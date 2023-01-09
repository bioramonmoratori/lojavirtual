package com.lojavirtual.apiprodutos.classesComuns;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.lojavirtual.apiprodutos.dto.PedidoDto;
import com.lojavirtual.apiprodutos.dto.ProdutoDto;
import com.lojavirtual.apiprodutos.dto.ProdutoSolicitacaoDto;
import com.lojavirtual.apiprodutos.service.ProdutosService;

@Component
public class ConfirmaEReservaProduto {
	
	@Autowired
	ProdutosService produtosService;
	
	public boolean confirmaProduto(PedidoDto listaDePedidos, ProdutosService produtosService) {
		
		this.produtosService = produtosService;
		
    	//Confere se todos os produtos da lista existem
    	for(int i = 0; i < listaDePedidos.getListaDeProdutos().size(); i++)
    	{
    	    ProdutoSolicitacaoDto produtoSolicitacaoDto = listaDePedidos.getListaDeProdutos().get(i);
    	    
    	    if(produtosService.conferirExistenciaDoProdutoPorId(produtoSolicitacaoDto.getIdDeEstoque()) == false) {
    	    	System.out.println("Produto nao encontrado");
    	    	return false;
    	    }
    	    
    	}
    	
    	//Confere se todos os produtos da lista tem quantidade suficiente em estoque disponivel
    	for(int i = 0; i < listaDePedidos.getListaDeProdutos().size(); i++)
    	{
    		ProdutoSolicitacaoDto produtoSolicitacaoDto = listaDePedidos.getListaDeProdutos().get(i);
    		
    		ProdutoDto produto = produtosService.buscarProdutoPorId(produtoSolicitacaoDto.getIdDeEstoque());
    		
    		if(produtoSolicitacaoDto.getQuantidade() > produto.getEstoqueDisponivel()) {
    			System.out.println("Sem quantidade suficiente");
    			return false;
    		}
    		
    	}
    	
		return true;
		
	}

	public List<ProdutoSolicitacaoDto> reservarProdutos(PedidoDto listaDePedidos, List<ProdutoSolicitacaoDto> produtosReservados) {
		
    	//Reserva cada produto
    	for(int i = 0; i < listaDePedidos.getListaDeProdutos().size(); i++)
    	{
    		ProdutoSolicitacaoDto produtoSolicitacaoDto = listaDePedidos.getListaDeProdutos().get(i);
    		
    		try {
    			
    			//Reserva no banco de dados
    			produtosService.reservarEstoque(produtoSolicitacaoDto.getIdDeEstoque(), produtoSolicitacaoDto.getQuantidade());
    			ProdutoDto produto = produtosService.buscarProdutoPorId(produtoSolicitacaoDto.getIdDeEstoque());
    			
    			produtoSolicitacaoDto.converter(produto);
    			
    			//Adiciona o produto reservado na lista de retorno
    			produtosReservados.add(produtoSolicitacaoDto);
    			
    			
    			

    			
    		} catch(Exception e) {
    			return null;
    		}
    		
    		
    	}
    	return produtosReservados;
		
	}

}
