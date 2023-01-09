package com.lojavirtual.apipedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lojavirtual.apipedidos.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{
	

}
