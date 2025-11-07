🚀 DeliveryTech - API de Delivery

API REST desenvolvida em Spring Boot 3 + Java 21, simulando o backend de uma plataforma de delivery (similar ao iFood e Uber Eats).

Este projeto é o núcleo do sistema DeliveryTech, contendo camadas de Controllers, Services, Repositories e DTOs, além de regras de negócio e transações.

🧩 Tecnologias utilizadas

☕ Java 21

⚙️ Spring Boot 3.3.5

🌐 Spring Web (API REST)

💾 Spring Data JPA (persistência)

🧠 ModelMapper (mapeamento DTO ↔ Entity)

🧱 H2 Database (banco em memória)

🧾 Bean Validation (validações com @NotNull, @Email, etc.)

🧰 Lombok (para reduzir boilerplate)

⚙️ Como executar o projeto

🔧 Pré-requisitos

JDK 21 instalado

Maven 3.9+

VS Code, IntelliJ ou Spring Tools Suite

🚀 Passos para rodar

Clonar o repositório

git clone https://github.com/seuusuario/delivery-api.git

Entrar na pasta do projeto

cd delivery-api

Rodar o projeto com Maven

mvn spring-boot:run

Acessar no navegador

http://localhost:8080

🧠 Estrutura de Pacotes

src/main/java/com/deliverytech/delivery

├── controller → Endpoints REST

├── dto → Objetos de transferência de dados

├── entity → Entidades JPA (tabelas do sistema)

├── exception → Exceções personalizadas

├── repository → Interfaces JPA

├── service → Regras de negócio e transações

└── DeliveryApiApplication.java

🌍 Endpoints Principais

🧑‍💼 Cliente

Método Endpoint Descrição

POST /api/clientes Cadastrar novo cliente

GET /api/clientes/{id} Buscar cliente por ID

GET /api/clientes Listar clientes ativos

PUT /api/clientes/{id} Atualizar dados

PATCH /api/clientes/{id}/status Ativar/desativar cliente

🍔 Restaurante

Método Endpoint Descrição

POST /api/restaurantes Cadastrar restaurante

GET /api/restaurantes/{id} Buscar restaurante

GET /api/restaurantes Listar disponíveis

GET /api/restaurantes/categoria/{categoria} Filtrar por categoria

🛒 Produto

Método Endpoint Descrição

POST /api/produtos Cadastrar produto

GET /api/produtos/{id} Buscar produto

GET /api/restaurantes/{id}/produtos Listar produtos do restaurante

PATCH /api/produtos/{id}/disponibilidade Alterar disponibilidade

📦 Pedido

Método Endpoint Descrição

POST /api/pedidos Criar pedido (transação completa)

GET /api/pedidos/{id} Buscar pedido completo

GET /api/clientes/{id}/pedidos Histórico do cliente

PATCH /api/pedidos/{id}/status Atualizar status

DELETE /api/pedidos/{id} Cancelar pedido

🧪 Exemplos de Requisições

🧍‍♂️ Cadastrar Cliente

POST /api/clientes

{

"nome": "João Silva",

"email": "joao@email.com",

"telefone": "11999999999",

"endereco": "Rua A, 123"

}

🧾 Criar Pedido

POST /api/pedidos

{

"clienteId": 1,

"restauranteId": 1,

"enderecoEntrega": "Rua B, 456",

"itens": [

{"produtoId": 1, "quantidade": 2},

{"produtoId": 2, "quantidade": 1}

]

}

🧰 Banco de Dados H2

Após iniciar o projeto, acesse:

http://localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:deliverydb

Usuário: sa

Senha: (deixe em branco)

📄 Licença

Projeto desenvolvido para fins educacionais.

Feito com 💙 por Arthur Lanzoni.