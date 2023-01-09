package com.lojavirtual.apiprodutos.model;

import javax.annotation.Nonnull;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Produto {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Nonnull
	private String titulo;
	
	@Nonnull
	private String descricao;
	
	@Nonnull
	private Long valorUnitario;
	
	@Nonnull
	private int estoqueDisponivel;
	
	@Nonnull
	private int estoqueReservado;
	
	@Nonnull
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
