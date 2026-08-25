# FinMan Identity

Serviço de identidade do ecossistema FinMan. Ele é responsável pelo cadastro de usuários, validação de credenciais e emissão de access tokens JWT.

> Estado atual: projeto em evolução. A proteção completa dos endpoints, a validação de JWT no FinMan e o isolamento de demonstrativos por proprietário são entregas planejadas do MVP.

## Responsabilidades

- Cadastro de usuários com nome, e-mail, documento e senha.
- Persistência de usuários em MySQL.
- Hash de senha com BCrypt.
- Login por e-mail e senha.
- Emissão de access token JWT.

## Arquitetura

Este serviço é a fonte de verdade dos dados de identidade. A API financeira (`finman`) receberá e validará o JWT localmente para associar operações financeiras ao usuário autenticado.

A decisão e seus trade-offs estão registrados no ADR do repositório principal do FinMan.

## Tecnologias

- Kotlin e Spring Boot
- Spring MVC, Spring Security e Bean Validation
- Spring Data JPA / Hibernate
- MySQL
- JJWT
- JUnit 5, Mockito Kotlin e H2
- Maven e Docker Compose

## Execução local

### Pré-requisitos

- Java 21
- Docker e Docker Compose
- Maven Wrapper

### Configuração

1. Copie `.env.example` para `.env`.
2. Substitua todos os valores de exemplo por valores locais.
3. Inicie o banco de dados:

```bash
docker compose up -d mysql
```

4. Inicie a aplicação:

```bash
./mvnw spring-boot:run
```

No Windows, use `mvnw.cmd spring-boot:run`.

A aplicação inicia na porta `8081`.

## Testes

```bash
./mvnw test
```

## Segurança de configuração

- `.env` não é versionado.
- `.env.example` documenta apenas nomes de variáveis e valores fictícios.
- Senhas, tokens e chaves não devem ser registrados em logs nem enviados ao repositório.

## Próximos passos do MVP

- Concluir separação entre `PasswordHasher` e `TokenService`.
- Implementar validação JWT e respostas padronizadas para `401` e `403`.
- Expor `/auth/login` e `/auth/me`.
- Integrar autorização do FinMan por `ownerId`.
- Containerizar o serviço e publicar uma imagem versionada.
