package com.lojavirtual.apipedidos.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.lojavirtual.apipedidos.model.Pedido;
import com.lojavirtual.apipedidos.model.Produto;

public class CadastroDePedidoDto {
	
	private Long id;
	private List<Produto> listaDeProdutos = new ArrayList<>();
	//Aqui deve ter a Conta do usuario tambem
	
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
	
	//Converter Model em Dto
	public void converter(Pedido pedido) {
		
		this.id = pedido.getId();
		this.listaDeProdutos = pedido.getListaDeProdutos();
		
	}
	
}
