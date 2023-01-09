package com.lojavirtual.apiprodutos.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.lojavirtual.apiprodutos.dto.PedidoDto;

@Component
public class EstoqueSolicitacaoConsumer {
	
	RestTemplate restTemplate = new RestTemplate();
	
	@RabbitListener(queues = "estoque.solicitacao")
	public void processMessage(PedidoDto pedidoDto) { 
		
		String uri = "http://localhost:8082/apiprodutos/produtos/reservarpedido/";
		restTemplate.postForObject(uri, pedidoDto, Void.class);
	}
	
}
