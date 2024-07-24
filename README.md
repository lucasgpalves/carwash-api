# Sistema de Gerenciamento de Lava Jato

Este é um sistema de gerenciamento para um lava jato, desenvolvido em Spring Boot com H2 Database(para testes). O sistema permite gerenciar a lavagens diárias, um sistema de Kanban para os carros (Esperando, Lavando, Secando, Pronto), gerar o faturamento do dia e calcular os ganhos dos funcionários com base em uma porcentagem diferente para cada um.

## Funcionalidades

- **Gerenciamento de Carros:**
  - Adicionar carros.
  - Atualizar o status dos carros (Esperando, Lavando, Secando, Pronto).
  - Listar carros por status.

- **Outros:**
  - Gerar o faturamento do dia.

## Estrutura do Projeto

```files
src
└── main
    ├── java
    │   └── com
    │       └── lavajato
    │           ├── controller
    │           │   ├── CarController.java
    │           │   ├── OwnerController.java    
    │           │   └── WashController.java
    │           ├── model
    │           │   ├── Car.java
    │           │   ├── CarStatus.java
    │           │   ├── Owner.java
    │           │   └── Wash.java
    │           ├── repository
    │           │   ├── CarRepository.java
    │           │   ├── OwnerRepository.java
    │           │   └── WashRepository.java
    │           ├── request
    │           │   ├── CarRequest.java
    │           │   ├── OwnerRequest.java
    │           │   └── WashRequest.java
    │           ├── response
    │           │   ├── CarResponse.java
    │           │   ├── OwnerResponse.java
    │           │   └── WashResponse.java
    │           ├── service
    │           │   ├── CarService.java
    │           │   ├── OwnerService.java
    │           │   └── WashService.java
    │           └── LavajatoApplication.java
    └── resources
        ├── db
        │    └── migration
        └── application.properties
```

## Requisitos

- Java 11 ou superior
- Maven 3.6.0 ou superior

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
POST /api/cars
```
```
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
    "status": "A_FAZER",
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
