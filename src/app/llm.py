from __future__ import annotations
import os
from typing import Optional, Dict, Any
from dotenv import load_dotenv
load_dotenv()

def have_openai() -> bool:
    return bool(os.getenv("OPENAI_API_KEY"))

def get_env_model(key: str, default: str) -> str:
    return os.getenv(key, default)

def chat_complete(model: str, system: str, user: str, temperature: float = 0.2,
                  response_format: Optional[Dict[str, Any]] = None, max_tokens: Optional[int] = None) -> str:
    from openai import OpenAI
    client = OpenAI(api_key=os.getenv("OPENAI_API_KEY"), base_url=os.getenv("OPENAI_BASE_URL","https://api.openai.com/v1"))
    kwargs: Dict[str, Any] = {}
    if response_format: kwargs["response_format"] = response_format
    if max_tokens is not None: kwargs["max_tokens"] = max_tokens
    resp = client.chat.completions.create(
        model=model,
        messages=[{"role":"system","content":system},{"role":"user","content":user}],
        temperature=temperature, **kwargs
    )
    return resp.choices[0].message.content or ""
