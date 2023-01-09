package com.lojavirtual.apiprodutos.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lojavirtual.apiprodutos.classesComuns.ConfirmaEReservaProduto;
import com.lojavirtual.apiprodutos.dto.PedidoDto;
import com.lojavirtual.apiprodutos.dto.ProdutoDto;
import com.lojavirtual.apiprodutos.dto.ProdutoSolicitacaoDto;
import com.lojavirtual.apiprodutos.service.ProdutosService;

@RestController
@RequestMapping("/produtos")
public class ProdutosController {
	
	@Autowired
	ProdutosService produtosService;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private ConfirmaEReservaProduto confirmaEReservaProduto;
	
    @GetMapping()
    public List<ProdutoDto> listarTodos() {
        return produtosService.listarProdutos();
    }
    
    @PostMapping("/reservarpedido")
    public PedidoDto reservarPedido(@RequestBody PedidoDto listaDePedidos){
    	
    	List<ProdutoSolicitacaoDto> produtosReservados = new ArrayList<ProdutoSolicitacaoDto>();
    	
    	confirmaEReservaProduto = new ConfirmaEReservaProduto();
    	
    	Boolean resultadoConfirmacaoDeProdutos = confirmaEReservaProduto.confirmaProduto(listaDePedidos, produtosService);
    	
    	if(resultadoConfirmacaoDeProdutos == false) {
    		
    		//Publicando na fila de estoque.reservado com listaDeProdutos nula
    		listaDePedidos.setListaDeProdutos(null);
    		rabbitTemplate.convertAndSend("estoque.reservado", listaDePedidos);
    		return null;
    	}
    	
    	
    	List<ProdutoSolicitacaoDto> produtosReservadosEConfirmados = confirmaEReservaProduto.reservarProdutos(listaDePedidos, produtosReservados);
    	
    	//Adiciona a lista de produtos reservados no pedidoDto de retorno
    	listaDePedidos.setListaDeProdutos(produtosReservadosEConfirmados);
    	
    	//Publicando na fila de estoque.reservado
		rabbitTemplate.convertAndSend("estoque.reservado", listaDePedidos);
    	
    	return listaDePedidos;
    	
    }
    
    @PostMapping("/cadastrarproduto")
    public ResponseEntity cadastrarProduto(@RequestBody ProdutoDto produtoDto){
    	
    	produtosService.criarProduto(produtoDto);
    	return ResponseEntity.ok(produtoDto);
    	
    }
    
    
    
    @DeleteMapping("/deletarproduto/{id}")
    public ResponseEntity<String> deletarProduto(@PathVariable Long id) {
    	
    	String json = "{\"status\":\"Produto nao encontrado\"}";
    	
    	try {
    		Boolean lista = produtosService.deletarProduto(id);
    		return ResponseEntity.ok("{\"status\":\"Produto deletado\"}");
    	} catch(Exception e) {
    		return ResponseEntity.ok(json);
    	}
    	
   
    }
    

}
