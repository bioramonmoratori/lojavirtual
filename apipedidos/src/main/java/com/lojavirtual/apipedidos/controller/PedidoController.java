package com.lojavirtual.apipedidos.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lojavirtual.apipedidos.dto.CadastroDePedidoDto;
import com.lojavirtual.apipedidos.dto.PedidoDto;
import com.lojavirtual.apipedidos.service.PedidosService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	
	@Autowired
	PedidosService pedidosService;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
    @PostMapping()
    public ResponseEntity criarPedido(@RequestBody CadastroDePedidoDto cadastroDePedidoDto) {
    	
    	//Cria o pedido no banco de dados da API Pedidos
    	pedidosService.criarPedido(cadastroDePedidoDto);
    	
    	//Envia a mensagem via RabbitMQ
    	rabbitTemplate.convertAndSend("estoque.solicitacao", cadastroDePedidoDto);
    	
        return ResponseEntity.ok(cadastroDePedidoDto);
    }
    
    @PostMapping("/pedidoReservadoEmEstoque")
    public ResponseEntity pedidoReservadoEmEstoque(@RequestBody CadastroDePedidoDto cadastroDePedidoDto) {
    	
    	//Muda no banco de dados o Pedido para iniciar o processo de pagamento
    	PedidoDto pedidoDto = pedidosService.pedidoReservadoEmEstoque(cadastroDePedidoDto);
    	
    	//Envia uma mensagem para a fila de pagamentos
    	rabbitTemplate.convertAndSend("estoque.aguardandopagamento", pedidoDto);
    	
    	//Retorna o pedido gerado
    	return ResponseEntity.ok(cadastroDePedidoDto);
    	
    }
}
