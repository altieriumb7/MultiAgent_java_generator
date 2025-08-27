from pathlib import Path
from typing import Dict

def ensure_dir(path: Path)->None:
    path.mkdir(parents=True, exist_ok=True)

def write_artifacts(base_dir: Path, artifacts: Dict[Path,str])->None:
    for rel, content in artifacts.items():
        full = base_dir / rel; full.parent.mkdir(parents=True, exist_ok=True); full.write_text(content, encoding='utf-8')
