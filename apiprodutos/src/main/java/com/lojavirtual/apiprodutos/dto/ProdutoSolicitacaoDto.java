package com.lojavirtual.apiprodutos.dto;

public class ProdutoSolicitacaoDto {
	
	private Long id;
	private String titulo;
	private String descricao;
	private Long valorUnitario;
	private Long quantidade;
	private Long valorTotal;
	private Long idDeEstoque;
	
	//Getters e Setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Long getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(Long valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	public Long getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}
	public Long getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(Long valorTotal) {
		this.valorTotal = valorTotal;
	}
	public Long getIdDeEstoque() {
		return idDeEstoque;
	}
	public void setIdDeEstoque(Long idDeEstoque) {
		this.idDeEstoque = idDeEstoque;
	}
	
	public void converter(ProdutoDto produto) {
		this.titulo = produto.getTitulo();
		this.descricao = produto.getDescricao();
		this.valorUnitario = produto.getValorUnitario();
		this.valorTotal = produto.getValorUnitario() * this.quantidade;
	}
	
	
}
