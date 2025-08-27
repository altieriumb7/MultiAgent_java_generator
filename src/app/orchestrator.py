from __future__ import annotations
from pathlib import Path
from typing import Dict, Any, Optional
try:
    from crewai import Agent, Task, Crew, Process
except Exception:
    class Agent:  # type: ignore
        def __init__(self, **kwargs): self.kwargs = kwargs
    class Task:  # type: ignore
        def __init__(self, **kwargs): self.kwargs = kwargs
    class Crew:  # type: ignore
        def __init__(self, agents, tasks, process): self.agents, self.tasks, self.process = agents, tasks, process
        def kickoff(self, **kwargs): return {"status": "stubbed"}
    class Process:  # type: ignore
        sequential = "sequential"

from .tools.parser import parse_rfa_to_spec, parse_rfa_to_spec_llm
from .tools.patterns import mine_project_style
from .tools.codegen import render_artifacts
from .tools.testgen import render_tests
from .tools.fs import write_artifacts
from .llm import get_env_model

from typing import Optional
from typing import Optional
from crewai import Agent, Task, Crew, Process

def build_crew(model: Optional[str] = None):
    # Agents (backstory is REQUIRED)
    doc_parser = Agent(
        role="DocParser",
        goal="Extract endpoints/entities from RFA",
        backstory="You are a senior analyst who reads RFAs and produces a clean, normalized REST spec."
    )
    pattern_miner = Agent(
        role="PatternMiner",
        goal="Infer conventions (Java version, Spring Boot, Lombok, records) from an existing Maven project",
        backstory="You scan pom.xml and the source tree to infer code/test style and tech choices."
    )
    code_agent = Agent(
        role="Codegen",
        goal="Generate Java/Spring code following the inferred project style",
        backstory="You are a senior Java/Spring engineer producing DTOs, Controller, Service, Client, Mapper, and properties."
    )
    test_agent = Agent(
        role="TestGen",
        goal="Generate JUnit tests consistent with the project’s stack",
        backstory="You create JUnit tests using Mockito/MockMvc (or project defaults) to validate generated components."
    )

    # Tasks (expected_output is REQUIRED)
    t1 = Task(
        description="Parse RFA to normalized spec",
        expected_output=(
            "A Python dict: {'endpoints': ["
            "{'use_case': str, 'path': str, 'method': str, "
            "'request': [{'name': str, 'type': str, 'constraints': str}], "
            "'response': [{'name': str, 'type': str, 'constraints': str}], "
            "'key': str}"
            "]}"
        ),
        agent=doc_parser,
    )

    t2 = Task(
        description="Mine project style from existing codebase",
        expected_output=(
            "A Python dict 'style' with keys: base_package, use_mapstruct, use_feign, use_webflux, "
            "junit, use_mockmvc, controller_suffix, service_suffix, mapper_suffix, client_suffix, "
            "properties_prefix, java_version, spring_boot_major, use_jakarta, use_lombok, use_records, test_package."
        ),
        agent=pattern_miner,
    )

    t3 = Task(
        description="Generate code artifacts",
        expected_output=(
            "A mapping {Path->str} for Java sources: dto/*.java, mapper/*.java, client/*.java, "
            "service/*.java, controller/*.java, and resources/application.properties."
        ),
        agent=code_agent,
    )

    t4 = Task(
        description="Generate tests",
        expected_output=(
            "A mapping {Path->str} for JUnit test sources: *Test.java under src/test/java "
            "for controller, service, mapper, and client."
        ),
        agent=test_agent,
    )

    return Crew(
        agents=[doc_parser, pattern_miner, code_agent, test_agent],
        tasks=[t1, t2, t3, t4],
        process=Process.sequential,
    )



def run_pipeline(crew, rfa_path: Path, out_dir: Path, project_root: Optional[Path],
                 use_llm: bool=False, models: Optional[Dict[str, Optional[str]]]=None) -> Dict[str, Any]:
    models = models or {}
    doc_model = models.get("doc") or get_env_model("DOC_MODEL", "gpt-4o-mini")
    text = rfa_path.read_text(encoding="utf-8")
    spec = parse_rfa_to_spec_llm(text, model=doc_model) if use_llm else parse_rfa_to_spec(text)
    style = mine_project_style(project_root) if project_root else {}
    artifacts = render_artifacts(spec, style); tests = render_tests(spec, style)
    all_files = {}; all_files.update(artifacts); all_files.update(tests); write_artifacts(out_dir, all_files)
    return {"status": "ok", "files": sorted([str(p) for p in all_files.keys()]), "style": style, "llm_used": bool(use_llm)}
