package com.lojavirtual.apipedidos.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.lojavirtual.apipedidos.enums.Status;

@Entity
public class Pedido {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//fetch = FetchType.EAGER, 
	@Nonnull
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Produto> listaDeProdutos = new ArrayList<>();
	
	//@Nonnull
	private Long contaId;
	
	@Nonnull
	private LocalDateTime dataDeCriacao;
	
	@Nonnull
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

	public void setConta(Long contaId) {
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
	
	public Long setValorTotalSomado(List<Produto> list) {
		Long valorTotalDosProdutos = (long) 0;

		for (int i = 0; i < list.size(); i++) {
			Produto item = list.get(i);
			
			valorTotalDosProdutos = valorTotalDosProdutos + item.getValorTotal();
		}
		
		return valorTotalDosProdutos;
	}
	
	
}
