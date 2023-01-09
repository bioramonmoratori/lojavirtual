package com.lojavirtual.apiprodutos.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lojavirtual.apiprodutos.dto.ProdutoDto;
import com.lojavirtual.apiprodutos.model.Produto;
import com.lojavirtual.apiprodutos.repository.ProdutoRepository;

@Service
public class ProdutosService {
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	public List<ProdutoDto> listarProdutos(){
		
		List<Produto> listaDeProdutos = produtoRepository.findAll();
		List<ProdutoDto> listaDeProdutosDto = new ArrayList<ProdutoDto>();
		
		for(int i = 0; i < listaDeProdutos.size(); i++)
		{
			ProdutoDto produtoDto = new ProdutoDto();
			produtoDto.converter(listaDeProdutos.get(i));
		    listaDeProdutosDto.add(produtoDto);
		}
		
		return listaDeProdutosDto;
		
	}
	
	public ProdutoDto criarProduto(ProdutoDto produtoDto) {
		
		Produto produto = new Produto();
		
		produto.setTitulo(produtoDto.getTitulo());
		produto.setDescricao(produtoDto.getDescricao());
		produto.setValorUnitario(produtoDto.getValorUnitario());
		produto.setEstoqueDisponivel(produtoDto.getEstoqueDisponivel());
		produto.setEstoqueReservado(produtoDto.getEstoqueReservado());
		produto.setEstoqueVendido(produtoDto.getEstoqueVendido());
		
		produtoRepository.save(produto);
		
		produtoDto.setId(produto.getId());
		
		return produtoDto;
	}
	
	public ProdutoDto buscarProdutoPorId(Long id) {
		
		Optional<Produto> produtoOptional;
		Produto produto;
		ProdutoDto produtoDto = new ProdutoDto();
		
		try {
			produtoOptional = produtoRepository.findById(id);
			produto = produtoOptional.get();
			produtoDto.converter(produto);
			
			return produtoDto;
			
		} catch(Exception e) {
			return produtoDto;
		}
	}
	
	public boolean conferirExistenciaDoProdutoPorId(Long id) {
		
		Optional<Produto> produtoOptional;
		Produto produto;
		
		try {
			produtoOptional = produtoRepository.findById(id);
			return true;
			
		} catch(Exception e) {
			return false;
		}
	}
	
	public boolean adicionarEstoque(Long id, int quantidade) {
		
		//Busco pelo ID
		Optional<Produto> produtoOptional;
		Produto produto;
		
		try {
			produtoOptional = produtoRepository.findById(id);
			produto = produtoOptional.get();
			
			int quantidadeAnterior = produto.getEstoqueDisponivel();
			produto.setEstoqueDisponivel(quantidadeAnterior + quantidade);
			
			//Alterar no banco de dados
			produtoRepository.adicionarEstoque(produto.getEstoqueDisponivel(), produto);
			
			return true;
			
		} catch(Exception e) {
			return false;
		}
	}
	
	public boolean reservarEstoque(Long id, Long quantidade) {
		
		//Busco pelo ID
		Optional<Produto> produtoOptional;
		Produto produto;
		
		try {
			produtoOptional = produtoRepository.findById(id);
			produto = produtoOptional.get();
			
			int quantidadeDisponivelAnterior = produto.getEstoqueDisponivel();
			int quantidadeReservadaAnterior = produto.getEstoqueReservado();
			
			produto.setEstoqueDisponivel((int) (quantidadeDisponivelAnterior - quantidade));
			produto.setEstoqueReservado((int) (quantidadeReservadaAnterior + quantidade));
			
			//Alterar no banco de dados
			produtoRepository.adicionarEstoque(produto.getEstoqueDisponivel(), produto);
			produtoRepository.reservarEstoque(produto.getEstoqueReservado(), produto);
			
			return true;
			
		} catch(Exception e) {
			throw e;
		}
	}
	
	public boolean venderEstoque(Long id, int quantidade) {
		
		//Busco pelo ID
		Optional<Produto> produtoOptional;
		Produto produto;
		
		try {
			produtoOptional = produtoRepository.findById(id);
			produto = produtoOptional.get();
			
			int quantidadeReservadaAnterior = produto.getEstoqueReservado();
			int quantidadeVendidaAnterior = produto.getEstoqueVendido();
			
			produto.setEstoqueReservado(quantidadeReservadaAnterior - quantidade);
			produto.setEstoqueVendido(quantidadeVendidaAnterior + quantidade);
			
			//Alterar no banco de dados
			produtoRepository.reservarEstoque(produto.getEstoqueReservado(), produto);
			produtoRepository.venderEstoque(produto.getEstoqueVendido(), produto);
			
			return true;
			
		} catch(Exception e) {
			return false;
		}
	}
	
	public boolean deletarProduto(Long id) {
		try {
			produtoRepository.deleteById(id);	
			return true;
			
		} catch(Exception e) {
			return false;
		}
	}
	
}
