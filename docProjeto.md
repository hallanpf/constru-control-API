# Controle de Versões

## Sprint 1

- **Gabriel**
    - RF001
    - RF002
- **Guilherme**
    - RF006
    - RF007
    - RF008
- **Hallan**
    - RF003
- **Jhonatan**
    - RF004
    - Repositorio no GitHub Configuração e Criação

# Descrição do Projeto

Neste projeto, foram criadas rotas e métodos para inserção, listagem geral e por parâmetro, além de deleção de
entidades. Abaixo, estão as informações essenciais para começar a usar o projeto:

## Como Testar

Para testar as rotas, siga estas instruções:

1. **Ambiente de Desenvolvimento:** Utilize o IntelliJ IDEA para executar o projeto.
2. **Teste de Endpoints:** Utilize o Postman ou Insomnia para testar os endpoints disponíveis.
3. **URL Base:** A URL base para acessar os endpoints é `http://localhost:8080`.
4. **Swagger:** Acesse a documentação do Swagger para ver os endpoints
   disponíveis. `http://localhost:8080/swagger-ui/index.html`

## Observações

- Ao testar a rota `/clients`, é necessário passar o corpo da requisição conforme o exemplo abaixo:

    ```json
    {
        "name": "Hallan P F",
        "phone": "123456",
        "email": "hallan@gmail.com",
        "cpf": "12334567",
        "rg": "123434",
        "maritalStatus": "CASADO"
    }
    ```

#Endpoints

- Clients:
    - GET: /clients
    - GET: /clients/{id}
    - POST: /clients
        - Exemplo Corpo de requisição:
            - Sem endereço:
              ```json
              {
              "name": "Hallan P F",
              "phone": "123456",
              "email": "hallan@gmail.com",
              "cpf": "12334567",
              "rg": "123434",
              "maritalStatus": "CASADO"
              }
              ``` 
              Resposta:
              ```json
              {
                "id": 1,
                "name": "Hallan P F",
                "cpf": "12334567",
                "rg": "123434",
                "phone": "123456",
                "email": "hallan@gmail.com",
                "userType": "CLIENTE",
                "address": null,
                "maritalStatus": "CASADO"
                }
              ``` 
                - Com endereço:
                  ```json 
                    { 
        
                    "id": 1,
                    "name": "Hallan P F",
                    "cpf": "12334567",
                    "rg": "123434",
                    "phone": "123456",
                    "email": "hallan@gmail.com",
                    "userType": "CLIENTE",
                    "maritalStatus": "CASADO"
                    "address": { 
                    "zipCode": "12345678",
                    "streetAddress": "Rua ABC 123",
                    "neighborhood": "Centro",
                    "city": "São Paulo",
                    "state": "SP"
                    }
                  } 

    - PATCH: /clients/{id}
    - DELETE: /clients/{id}

- Managers:
    - GET: /managers
    - GET: /managers/{id}
    - POST: /managers
      - Exemplo Corpo de requisição:
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
        Resposta:
        ```json
        { 
        "id": 1,
        "name": "Hallan P F",
        "cpf": "12334567",
        "rg": "123434",
        "phone": "123456",
        "userType": "GESTOR",
        "email": "", 
        "address": {
        "zipCode": "12345678",
        "streetAddress": "Rua ABC 123",
        "neighborhood": "Centro",
        "city": "São Paulo",
        "state": "SP"
        }
        }
        ```
        
    - PATCH: /managers/{id}
    - DELETE: /managers/{id}
    - Broker:
        - GET: /brokers
        - GET: /brokers/{id}
          - POST: /brokers
            - Exemplo Corpo de requisição:
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
        - PATCH: /brokers/{id}
        - DELETE: /brokers/{id}


