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
- Spring (JPA, REST, Cloud-Netflix, AMQP) [v.2.7.6]
- MariaDB [v.10.6.11] 
- Postman
- Docker [v.2.10.21]
- Maven [v.4.0.0]


## Endpoints

### Gateway

A URI principal onde estão reunidos todos os endpoints é **http://localhost:8082/**

### API Pedidos ( /apipedidos/pedidos ):

- `POST`  "/" : *Cria um pedido* | JSON - Body Parameter:

```
  {
    "listaDeProdutos":[
      {
        "idDeEstoque":1,
        "quantidade":1
      },
      {
        "idDeEstoque":2,
        "quantidade":3
      }
    ]
  }
  
```

*o id de estoque representa o número do ID do produto contido no banco de dados da API de Produtos* 

### API Produtos ( /apiprodutos/produtos ):

- `GET`  "/" : *Lista todos os produtos do estoque*
- `POST` "/cadastrarproduto" : *Cadastra um novo produto no estoque* | JSON - Body Parameter:

```
{
    "titulo":"PS4",
    "descricao":"PS4 + 2 Controles",
    "valorUnitario":2500.00,
    "estoqueDisponivel":5,
    "estoqueReservado":2,
    "estoqueVendido":0
}
  
```

- `DELETE` "/deletarproduto/{id}" : *Deleta um produto no estoque pelo ID* | Path Variable


## Fluxo do Projeto

## Contatos
- `LinkedIn` : https://www.linkedin.com/in/ramon-moratori-86a558243/
- `E-mail` : ramon.moratori@estudante.ufjf.br
