<h1 align="center">
  Back-End - Loja Virtual
</h1>

<p>
  Projeto de uma loja virtual com controle de estoque de produtos físicos. A aplicação conta com arquitetura de microsserviços e comunicação em mensageria.
</p>

## :hammer: Microsserviços

- `API de Produtos`: Informa a lista de produtos e gerencia a disponibilidade deles de acordo com a demanda de pedidos 
- `API de Pedidos`: Cria e gerencia requisições de pedidos, estabelecendo comunicação com os demais serviços
- `API de Pagamentos`: *Em construção*

## Tecnologias Utilizadas

- Java [v.17]
- RabbitMQ [v.3.11]
- Spring (JPA, REST, Eureka, AMQP) [v.2.7.6]
- MariaDB [v.10.6.11] 
- Postman (teste de requisições)
- Docker
- Maven [v.4.0.0]

