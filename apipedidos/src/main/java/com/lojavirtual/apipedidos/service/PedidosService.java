package com.lojavirtual.apipedidos.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.lojavirtual.apipedidos.dto.CadastroDePedidoDto;
import com.lojavirtual.apipedidos.dto.PedidoDto;
import com.lojavirtual.apipedidos.enums.Status;
import com.lojavirtual.apipedidos.model.Pedido;
import com.lojavirtual.apipedidos.model.Produto;
import com.lojavirtual.apipedidos.repository.PedidoRepository;

@Service
public class PedidosService {
	
	@Autowired
	PedidoRepository pedidoRepository;
	
	public CadastroDePedidoDto criarPedido(CadastroDePedidoDto cadastroDePedidoDto) {
		
		Pedido pedido = new Pedido();
		
		pedido.setListaDeProdutos(cadastroDePedidoDto.getListaDeProdutos());
		pedido.setConta(null);
		pedido.setDataDeCriacao(LocalDateTime.now());
		pedido.setStatus(Status.PROCESSANDO);
		pedido.setValorTotal(null);
		
		pedidoRepository.save(pedido);
		
		cadastroDePedidoDto.setId(pedido.getId());
		
		return cadastroDePedidoDto;
	}
	
	public PedidoDto pedidoReservadoEmEstoque(CadastroDePedidoDto cadastroDePedidoDto) {
		
		//Localiza pedido no banco
		Pedido pedidoNoBancoDeDados = pedidoRepository.findById(cadastroDePedidoDto.getId()).get();
		
		//Alterando informacoes
		List<Produto> listaDeProdutos = cadastroDePedidoDto.getListaDeProdutos();
		Status status = Status.AGUARDANDO_CONFIRMACAO_DE_PAGAMENTO;
		Long valorTotalSomado = pedidoNoBancoDeDados.setValorTotalSomado(listaDeProdutos);
		
		//Alterando no banco de dados
		pedidoNoBancoDeDados.setListaDeProdutos(listaDeProdutos);
		pedidoNoBancoDeDados.setStatus(status);
		pedidoNoBancoDeDados.setValorTotal(valorTotalSomado);
		pedidoRepository.save(pedidoNoBancoDeDados);
		
		//Retorna PedidoDto atualizado
		Pedido pedidoProntoParaPagar = pedidoRepository.findById(pedidoNoBancoDeDados.getId()).get();
		
		PedidoDto pedidoProntoParaPagarDto = new PedidoDto();
		pedidoProntoParaPagarDto.converter(pedidoProntoParaPagar);
		
		return pedidoProntoParaPagarDto;
	}

}
