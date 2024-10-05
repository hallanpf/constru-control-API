# Informações do Projeto ConstruControl
## Documentação do Projeto
| Disciplina      | Tecnologias para BackEnd                                                                                   |
|-----------------|------------------------------------------------------------------------------------------------------------|
| Professora      | Rodrigo Fujioka                                                                                            |
| Nome do projeto | ConstruControl                                                                                             |
| Equipe          | Gabriel Marques, <br/>Guilherme de Medeiros Ferreira, <br/>Hallan Pedrosa Ferreira, <br/>Jhonatan Domingos |
| Líder           | Jhonatan Domingos                                                                                          |

# Projeto API Rest - Equipe

## Detalhamento do Time

Este projeto foi desenvolvido pela equipe, composta pelos seguintes membros:

- **Gabriel**
- **Guilherme**
- **Hallan**
- **Jhonatan**

Cada membro contribuiu com a implementação de um ou mais endpoints, além dos requisitos funcionais (RFs) discutidos em sala de aula.

### Detalhamento das Contribuições:

- **Gabriel:**

  Desenvolveu os requisitos funcionais RF001, RF002, e RF007 (Login, registro de usuário, autenticação, empreendimento).

- **Guilherme:**

  Desenvolveu os requisitos funcionais RF006, RF007, RF008, RF009, RF030, RF0028, RF0016, RF0029, e RF007 (Construtora, 
  empreendimento, fornecedor).

- **Hallan:**

  Desenvolveu os requisitos funcionais RF003, RF0027, RF0015, RF0026, e RF007 (Gestor, fornecedor, cliente,
  empreendimento).

- **Jhonatan:**

  Desenvolveu os requisitos funcionais RF004, RF0029, RF0014, RF005, RF0013, e RF007 (Cliente, corretor, fornecedor,
  swagger, empreendimento).

Além disso, a equipe trabalhou em conjunto na configuração e criação do repositório no GitHub.

## Como Testar

Para testar as rotas, siga estas instruções:

1. Ambiente de Desenvolvimento: Utilize o IntelliJ IDEA para executar o projeto.
2. Teste de Endpoints: Utilize o Postman ou Insomnia para testar os endpoints disponíveis.
3. URL Base: A URL base para acessar os endpoints é http://localhost:8080.
4. Swagger: Acesse a documentação do Swagger para ver os endpoints disponíveis em http://localhost:8080/swagger-ui/index.html

## Observações

### Autenticação e Autorização

1. Para cadastrar um usuário, use o seguinte formato:

   Cadastrando um gestor:
   ```json
   {
     "login": "gabrielmarques@email.com",
     "password": "123456",
     "userRole": "GESTOR"
   }
   ```

   Cadastrando um cliente:
   ```json
   {
     "login": "gabrielmarques@email.com",
     "password": "123456",
     "userRole": "CLIENTE"
   }
   ```

2. Para fazer login:
   ```json
   {
     "login": "gabrielmarques@email.com",
     "password": "123456"
   }
   ```

3. Após o login, você receberá um token de acesso. Use este token no cabeçalho "Authorization" das requisições, com o prefixo "Bearer".

## Endpoints

### Auth:
- POST: /auth/register
- POST: /auth/login

### Clients:
- GET: /clients
- GET: /clients/{id}
- POST: /clients
- PATCH: /clients/{id}
- DELETE: /clients/{id}

### Managers:
- GET: /managers
- GET: /managers/{id}
- POST: /managers
- PATCH: /managers/{id}
- DELETE: /managers/{id}

### Brokers:
- GET: /brokers
- GET: /brokers/{id}
- POST: /brokers
- PATCH: /brokers/{id}
- DELETE: /brokers/{id}

### Suppliers:
- GET: /suppliers
- GET: /suppliers/{id}
- POST: /suppliers
- DELETE: /suppliers/{id}

### Constructions:
- GET: /constructions
- GET: /constructions/{id}
- POST: /constructions
- DELETE: /constructions/{id}

### Companies:
- GET: /companies
- GET: /companies/{id}
- POST: /companies
- DELETE: /companies/{id}

### Apartments:
- GET: /apartments
- GET: /apartments/{id}
- POST: /apartments
- DELETE: /apartments/{id}

### Employees:
- GET: /employees
- GET: /employees/{id}
- POST: /employees

## Exemplos de Requisições

### Clients (POST):
```json
{
  "name": "Hallan P F",
  "phone": "123456",
  "email": "hallan@gmail.com",
  "cpf": "12334567",
  "rg": "123434",
  "maritalStatus": "CASADO",
  "address": {
    "zipCode": "12345678",
    "streetAddress": "Rua ABC 123",
    "neighborhood": "Centro",
    "city": "São Paulo",
    "state": "SP"
  }
}
```

### Managers (POST):
```json
{
  "name": "Hallan P F",
  "phone": "123456",
  "email": "test@mail.com",
  "cpf": "12334567",
  "rg": "123434",
  "address": {
    "zipCode": "12345678",
    "streetAddress": "Rua ABC 123",
    "neighborhood": "Centro",
    "city": "São Paulo",
    "state": "SP"
  }
}
```

### Brokers (POST):
```json
{
  "name": "Hallan P F",
  "phone": "123456",
  "email": "",
  "cpf": "12334567",
  "rg": "123434",
  "creci": "PB5555",
  "address": {
    "zipCode": "12345678",
    "streetAddress": "Rua ABC 123",
    "neighborhood": "Centro",
    "city": "São Paulo",
    "state": "SP"
  }
}
```

Observe que os gestores têm acesso a todo o ecossistema da aplicação, os corretores têm acesso apenas à lista de imóveis, e os clientes não têm acesso aos endpoints, pois possuem uma área do cliente com todas as informações.

Para os novos endpoints adicionados (Suppliers, Constructions, Companies, Apartments, Employees), siga o padrão de estrutura JSON semelhante aos exemplos fornecidos, adaptando os campos conforme necessário para cada entidade específica.\
