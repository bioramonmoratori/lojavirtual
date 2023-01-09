package com.lojavirtual.apipedidos.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.lojavirtual.apipedidos.dto.CadastroDePedidoDto;

@Component
public class EstoqueReservadoConsumer {
	
	RestTemplate restTemplate = new RestTemplate();

	@RabbitListener(queues = "estoque.reservado")
	public void processMessage(CadastroDePedidoDto cadastroDePedidoDto) { 
		
		String uri = "http://localhost:8082/apipedidos/pedidos/pedidoReservadoEmEstoque";
		restTemplate.postForObject(uri, cadastroDePedidoDto, Void.class);
		
	}
}
