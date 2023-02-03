package com.lojavirtual.loja.dto;

public class ProdutoDto {
	
	private Long id;
	private String titulo;
	private String descricao;
	private Long valorUnitario;
	private int estoqueDisponivel;
	private int estoqueReservado;
	private int estoqueVendido;
	
	
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
	public int getEstoqueDisponivel() {
		return estoqueDisponivel;
	}
	public void setEstoqueDisponivel(int estoqueDisponivel) {
		this.estoqueDisponivel = estoqueDisponivel;
	}
	public int getEstoqueReservado() {
		return estoqueReservado;
	}
	public void setEstoqueReservado(int estoqueReservado) {
		this.estoqueReservado = estoqueReservado;
	}
	public int getEstoqueVendido() {
		return estoqueVendido;
	}
	public void setEstoqueVendido(int estoqueVendido) {
		this.estoqueVendido = estoqueVendido;
	}
	
	
	
}
