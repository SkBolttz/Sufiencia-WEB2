# REST API Furb - Sistema de Gerenciamento de Funcionários, Comandas e Produtos

API RESTful desenvolvida com Spring Boot 3, que oferece funcionalidades para autenticação de funcionários, gestão de comandas e cadastro de produtos. A API está documentada com OpenAPI/Swagger para facilitar o consumo e teste dos endpoints.

---
Importar o arquivo Prova Suficiencia WEB.postman_collection.json para o Postman caso necessario para testes.
---

## Tecnologias utilizadas

- Java 17
- Spring Boot 3.3.4
- Spring Security
- Spring Data JPA
- PostgreSQL (banco de dados)
- Springdoc OpenAPI (Swagger UI)
- Jakarta Validation (Bean Validation)
- Maven
- Lombok

---

## Funcionalidades principais

### 1. Autenticação de Funcionários

- Endpoint: `POST /RestAPIFurb/login`
- Permite o login do funcionário utilizando CPF e senha.
- Retorna um token JWT para autenticação nas próximas requisições.

- Endpoint: `POST /RestAPIFurb/registro`
- Permite o cadastro de um novo funcionário.
- Valida duplicidade pelo CPF e dados obrigatórios.
- Retorna mensagem de sucesso ou erro.

---

### 2. Gerenciamento de Comandas

- Endpoint: `POST /RestAPIFurb/registrar/comanda`
  - Registra uma nova comanda informando preço, data e dados do responsável.
  
- Endpoint: `GET /RestAPIFurb/listar/comandas`
  - Lista todas as comandas cadastradas no sistema.
  
- Endpoint: `GET /RestAPIFurb/listar/comanda/{id}`
  - Retorna uma comanda específica pelo ID.
  
- Endpoint: `PUT /RestAPIFurb/editar/comanda/{id}`
  - Atualiza uma comanda existente pelo ID.
  
- Endpoint: `DELETE /RestAPIFurb/deletar/comanda/{id}`
  - Remove uma comanda cadastrada pelo ID.

Todas as operações lançam exceções específicas e retornam códigos HTTP adequados para erros, como dados não encontrados ou erros de validação.

---

### 3. Cadastro de Produtos

- Endpoint: `POST /RestAPIFurb/produto/cadastrar`
  - Permite cadastrar um novo produto com nome e preço.
  - Verifica duplicidade e valida os dados recebidos.

---

## Documentação Swagger

Após rodar a aplicação, a documentação dos endpoints estará disponível em: http://localhost:8080/swagger-ui/index.html

Lá é possível testar todos os endpoints de forma interativa.


