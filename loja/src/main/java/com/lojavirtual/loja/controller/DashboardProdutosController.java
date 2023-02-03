package com.lojavirtual.loja.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lojavirtual.loja.dto.ProdutoDto;
import com.lojavirtual.loja.service.ProdutosService;

@Controller
@RequestMapping("/")
public class DashboardProdutosController {
	
	@Autowired
	ProdutosService produtosService;
	
	@RequestMapping(value="/dashboard", method=RequestMethod.GET)
	public String dashboardProdutos(Model model, Integer page) {
		
		if(page == null) {
			page = 0;
		}
		
		List<ProdutoDto> lista = new ArrayList<>();
		Page<ProdutoDto> listaDeProdutos = null;
		
		try {
			lista = produtosService.listarProdutos();
		} catch(Exception e) {
			lista = null;
		}
		
		if(lista != null) {
	        // Calcula o índice inicial e final da sublista
	        int fromIndex = page * 6;
	        int toIndex = fromIndex + 6;
	        if (toIndex > lista.size()) {
	            toIndex = lista.size();
	        }
			
	        // Extrai a sublista da lista original
	        List<ProdutoDto> subLista = lista.subList(fromIndex, toIndex);
	        
			//Paginacao
			listaDeProdutos = new PageImpl<>(subLista, PageRequest.of(page, 6), lista.size());
		}
		model.addAttribute("listadeprodutos", listaDeProdutos);
		return "dashboardprodutos";
	}
	
	@RequestMapping(value="/novoproduto", method=RequestMethod.GET)
	public String novoProduto(ProdutoDto produtoDto, Model model) {
		
		model.addAttribute("produtoDto", produtoDto);
		return "formularionovoproduto";
	}
	
	@RequestMapping(value="/requisicaonovoproduto", method=RequestMethod.POST)
	public String requisicaoNovoProduto(@Valid ProdutoDto produtoDto, BindingResult binding) {

		try {
			produtosService.novoProduto(produtoDto, binding);
		} catch(Exception e) {
			return "redirect:http://localhost:8080/loja/falha";
		}
		return "redirect:http://localhost:8080/loja/dashboard/";
	
	}
	
	@RequestMapping(value="/falha", method=RequestMethod.GET)
	public String falha() {
		
		return "falha";
	}
	
}
