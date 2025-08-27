# Example RFA

## Use Case: User Registration
- Endpoint: /api/user/register
- Method: POST
- Request:
  - username: string, required
  - email: string, required
  - password: string, required, min=8
- Response:
  - userId: string
  - creationDate: datetime
