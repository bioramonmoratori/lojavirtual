package com.lojavirtual.loja.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.client.RestTemplate;

import com.lojavirtual.loja.dto.ProdutoDto;

@Service
public class ProdutosService {
	
	private final RestTemplate restTemplate = new RestTemplate();
	
	public List<ProdutoDto> listarProdutos(){
		
		List<ProdutoDto> listaDeProdutos = restTemplate.getForObject("http://localhost:8080/apiprodutos/produtos", ArrayList.class);
		
		return listaDeProdutos;
	}
	
	public void novoProduto(ProdutoDto produtoDto, BindingResult binding) throws Exception {
		
		String uri = "http://localhost:8080/apiprodutos/produtos/cadastrarproduto";
		
		if(!binding.getAllErrors().isEmpty()) {
			throw new Exception();
		}
		
		try {
			
			restTemplate.postForObject(uri, produtoDto, String.class);

		} catch(Exception e) {
			throw e;
		}
		
	}
	
}
