# Sistema de Gerenciamento de Lava Jato

Este é um sistema de gerenciamento para um lava jato, desenvolvido em Spring Boot com H2 Database(para testes). O sistema permite gerenciar a lavagens diárias, um sistema de Kanban para os carros (Esperando, Lavando, Secando, etc), gerar o faturamento do dia e calcular os ganhos dos funcionários com base em uma porcentagem diferente para cada um.

## Sumário
1. [Tecnologias](#tecnologias)
2. [Funcionalidades](#funcionalidades)
3. [Estrutura do Projeto](#estrutura-do-projeto)
4. [Requisitos](#requisitos)
5. [Documentação da API](#documentação-da-api)
   - [Documentação Programática](#documentação-programática)
   - [Configuração](#configuração)
6. [Configuração do Banco de Dados](#configuração-do-banco-de-dados)
7. [Compilação e Execução](#compilação-e-execução)
8. [Endpoints da API](#endpoints-da-api)
   - [Dono](#dono)
   - [Carros](#carros)
   - [Lavagens](#lavagens)
9. [Contribuição](#contribuição)
10. [Licença](#licença)

## Tecnologias
- Java
- Spring Boot
- JPA
- H2 Database
- Swagger

## Funcionalidades

- **Gerenciamento de Lavagens:**
  - Adicionar carros.
  - Atualizar o status das lavagens (Esperando, Lavando, Secando, Pronto).
  - Listar lavagens por status.

- **Entidades:**
  - Dono
  - Carro
  - Lavagem

## Estrutura do Projeto

```files
src
└── main
    ├── java
    │   └── com
    │       └── carwash
    │           ├── \controller 
    │           ├── \events
    │           ├── \listeners
    │           ├── \model
    │           ├── \repository
    │           ├── \request
    │           ├── \response
    │           ├── \service
    │           └── CarWashApplication.java
    └── resources
        ├── db
        │    └── migration
        └── application.properties
```

## Requisitos

- Java 11 ou superior
- Maven 3.6.0 ou superior

## Documentação da API

Este projeto utiliza o Springdoc OpenAPI para gerar a documentação dos endpoints da API.

### Documentação Programática

A documentação da API está disponível nos seguintes formatos, sem a interface gráfica do Swagger UI:

- **OpenAPI JSON**: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)
- **OpenAPI YAML**: [http://localhost:8080/v3/api-docs.yaml](http://localhost:8080/v3/api-docs.yaml)

Esses endpoints geram automaticamente a especificação OpenAPI, que pode ser utilizada para gerar clientes da API, integração com ferramentas de teste, ou qualquer outra ferramenta que suporte OpenAPI.

### Configuração

A configuração do Springdoc é realizada através da classe `OpenApiConfiguration`, localizada em `src/main/java/com/mycompany/carwash/config/OpenApiConfiguration.java`. Nessa classe, são definidos os detalhes da API, como título, versão e descrição:

```java
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Carwash API",
        version = "v1",
        description = "Documentação da API do sistema de gerenciamento de lava jato"
    )
)
public class OpenApiConfiguration {
    // Configurações adicionais podem ser adicionadas aqui
}
```

## Configuração do Banco de Dados

Certifique-se de ter um banco de dados PostgreSQL em execução e crie um banco de dados para o projeto.

Atualize o arquivo `application.properties` com as informações do seu banco de dados:

```properties
spring.datasource.url=jdbc:h2:mem:carwash
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.password=
spring.datasource.username=sa
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```

## Compilação e Execução
1. Clone o repositório:
```sh
git clone https://github.com/seu_usuario/seu_projeto.git
cd seu_projeto
```
2. Compile o projeto com Maven:
```sh
mvn clean install
```
3. Execute a aplicação
```sh
mvn spring-boot:run
```
A aplicação estará disponível em `http://localhost:8080`.

## Endpoints da API

### Dono
- Adicionar um Novo Dono
```http
POST /api/owners
```
```JSON
{
  "name": "Jonh Doe",
  "phoneNumber" : "123456789"
}
```

### Carros
- Listar Carros
```http
GET /api/cars
```

- Adicionar um Novo Carro
```http
POST /api/cars
```
Body para adicionar um novo Carro
```JSON 
{
  "licensePlate": "AAA1A11",
  "model": "Toyota Corolla",
  "color": "Preto",
	"ownerId": null
}
```

### Lavagens
- Listar Carros
```http
GET /api/cars
```

- Adicionar uma nova Lavangem
```http
POST /api/cars
```
Body para adicionar uma Nova Lavangem
```JSON 
{
  "carId": "Insira o Id do carro criado aqui",
  "description": "Americana + Cera",
  "amount": 70.00,
  "status": "PENDING",
  "isPaid": false
}
```


## Contribuição
1. Faça um fork do projeto
2. Crie uma branch para sua feature (git checkout -b feature/nova-feature)
3. Faça commit das suas mudanças (git commit -am 'Adiciona nova feature')
4. Faça push para a branch (git push origin feature/nova-feature)
5. Abra um Pull Request

## Licença
Distribuído sob a licença MIT. Veja `LICENSE` para mais informações.

---
Desenvolvido por [Lucas Alves](https://github.com/lucasgpalves)
