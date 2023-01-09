package com.lojavirtual.apipedidos.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.lojavirtual.apipedidos.enums.Status;
import com.lojavirtual.apipedidos.model.Pedido;
import com.lojavirtual.apipedidos.model.Produto;

public class PedidoDto {
	
	private Long id;
	private List<Produto> listaDeProdutos;
	private Long contaId;
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime dataDeCriacao;
	private Status status;
	private Long valorTotal;

	
	//Getters e Setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Produto> getListaDeProdutos() {
		return listaDeProdutos;
	}
	public void setListaDeProdutos(List<Produto> listaDeProdutos) {
		this.listaDeProdutos = listaDeProdutos;
	}
	public Long getContaId() {
		return contaId;
	}
	public void setContaId(Long contaId) {
		this.contaId = contaId;
	}
	public LocalDateTime getDataDeCriacao() {
		return dataDeCriacao;
	}
	public void setDataDeCriacao(LocalDateTime dataDeCriacao) {
		this.dataDeCriacao = dataDeCriacao;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Long getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(Long valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	public void converter(Pedido pedido) {
		this.id = pedido.getId();
		this.listaDeProdutos = pedido.getListaDeProdutos();
		this.contaId = pedido.getContaId();
		this.dataDeCriacao = pedido.getDataDeCriacao();
		this.status = pedido.getStatus();
		this.valorTotal = pedido.getValorTotal();
	}
	
}
