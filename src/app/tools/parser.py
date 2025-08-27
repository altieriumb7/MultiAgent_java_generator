from __future__ import annotations
import re, json
from typing import Dict, Any, List
from ..llm import have_openai, chat_complete

def parse_rfa_to_spec(text: str) -> Dict[str, Any]:
    blocks = re.split(r"\n\s*##\s*Use Case:\s*", text)
    endpoints: List[Dict[str, Any]] = []
    for b in blocks:
        if not b.strip() or b.strip().startswith('#'):
            continue
        name_line, *rest = b.strip().splitlines()
        name = name_line.strip()

        endpoint = _extract_value(rest, r"Endpoint:\s*(.+)")
        method = _extract_value(rest, r"Method:\s*(.+)")
        req_fields = _extract_fields(rest, section="Request:")
        res_fields = _extract_fields(rest, section="Response:")

        endpoints.append({
            "use_case": name,
            "path": endpoint,
            "method": method,
            "request": req_fields,
            "response": res_fields,
            "key": _make_key(name)
        })
    return {"endpoints": endpoints}

def parse_rfa_to_spec_llm(text: str, model: str = "gpt-4o-mini") -> Dict[str, Any]:
    if not have_openai():
        return parse_rfa_to_spec(text)
    system = "You are a senior backend architect. Extract REST endpoints from an RFA into strict JSON."
    user = f"""RFA TEXT:
{text}

Return JSON with this schema:
{{
  "endpoints": [ {{
     "use_case": "string",
     "path": "string",
     "method": "GET|POST|PUT|DELETE",
     "request": [{{"name":"string","type":"string","constraints":"string"}}],
     "response": [{{"name":"string","type":"string","constraints":"string"}}],
     "key": "dot.separated.name"
  }} ]
}}"""
    raw = chat_complete(model=model, system=system, user=user, temperature=0.1,
                        response_format={"type":"json_object"})
    try:
        data = json.loads(raw)
        for ep in data.get("endpoints", []):
            if not ep.get("key"):
                ep["key"] = _make_key(ep.get("use_case",""))
        return data
    except Exception:
        return parse_rfa_to_spec(text)

def _make_key(name: str) -> str:
    return re.sub(r"[^a-z0-9]+", ".", name.lower()).strip('.')

def _extract_value(lines: List[str], pattern: str) -> str:
    rx = re.compile(pattern, re.I)
    for line in lines:
        m = rx.search(line)
        if m:
            return m.group(1).strip()
    return ""

def _extract_fields(lines: List[str], section: str) -> List[Dict[str, Any]]:
    fields: List[Dict[str, Any]] = []
    active = False
    for line in lines:
        if line.strip().startswith(section):
            active = True
            continue
        if active:
            if not line.strip() or not line.strip().startswith('-'):
                if fields:
                    break
                else:
                    continue
            body = line.strip()[1:].strip()
            if ':' in body:
                name, meta = [x.strip() for x in body.split(':', 1)]
            else:
                name, meta = body.strip(), ''
            dtype = meta.split(',')[0].strip() if meta else 'string'
            constraints = ','.join([m.strip() for m in meta.split(',')[1:]]) if ',' in meta else ''
            fields.append({"name": name, "type": dtype, "constraints": constraints})
    return fields
