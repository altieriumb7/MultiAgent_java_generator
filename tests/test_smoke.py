from pathlib import Path
from src.app.orchestrator import run_pipeline, build_crew

def test_smoke(tmp_path: Path):
    out_dir = tmp_path / "out"
    crew = build_crew()
    r = run_pipeline(crew, Path("example_rfa.md"), out_dir, project_root=None)
    assert r["status"] == "ok"
    assert any("application.properties" in f for f in r["files"])
