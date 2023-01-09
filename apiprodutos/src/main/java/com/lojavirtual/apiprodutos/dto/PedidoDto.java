package com.lojavirtual.apiprodutos.dto;

import java.util.List;

public class PedidoDto {
	
	private Long id;
	private List<ProdutoSolicitacaoDto> listaDeProdutos;
	
	//Getters e Setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<ProdutoSolicitacaoDto> getListaDeProdutos() {
		return listaDeProdutos;
	}
	public void setListaDeProdutos(List<ProdutoSolicitacaoDto> listaDeProdutos) {
		this.listaDeProdutos = listaDeProdutos;
	}
	
}
