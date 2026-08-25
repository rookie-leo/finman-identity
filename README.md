# FinMan Identity

Serviço de identidade do ecossistema FinMan. É responsável pelo cadastro de usuários, validação de credenciais e emissão de access tokens JWT.

> Estado atual: projeto em evolução. A proteção completa dos endpoints, a validação de JWT no FinMan e o isolamento de demonstrativos por proprietário são entregas planejadas do MVP.

## Responsabilidades

- Cadastro de usuários com nome, e-mail, documento e senha.
- Persistência de usuários em MySQL.
- Hash de senha com BCrypt.
- Login por e-mail e senha.
- Emissão de access token JWT.

## Arquitetura

O projeto utiliza Arquitetura Hexagonal (Ports & Adapters):

```text
domain → port ← application → adapters