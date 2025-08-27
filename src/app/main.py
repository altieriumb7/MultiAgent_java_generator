from __future__ import annotations
import json
from pathlib import Path
from typing import Optional
import typer

from .orchestrator import build_crew, run_pipeline
from .tools.fs import ensure_dir

app = typer.Typer(add_completion=False)

@app.command()
def run(
    rfa_path: str = typer.Argument(...),
    out: str = typer.Option("./generated", "--out", "-o"),
    project_root: Optional[str] = typer.Option(None, "--project-root", "-p"),
    model: Optional[str] = typer.Option(None, "--model"),
    use_llm: bool = typer.Option(False, "--use-llm"),
    doc_model: str = typer.Option(None, "--doc-model"),
    pattern_model: str = typer.Option(None, "--pattern-model"),
    code_model: str = typer.Option(None, "--code-model"),
    test_model: str = typer.Option(None, "--test-model"),
):
    out_dir = Path(out); ensure_dir(out_dir)
    crew = build_crew(model=model)
    result = run_pipeline(
        crew=crew,
        rfa_path=Path(rfa_path),
        out_dir=out_dir,
        project_root=Path(project_root) if project_root else None,
        use_llm=use_llm,
        models={"doc": doc_model, "pattern": pattern_model, "code": code_model, "test": test_model}
    )
    print("\n=== PIPELINE RESULT ==="); print(json.dumps(result, indent=2))

if __name__ == "__main__":
    app()
