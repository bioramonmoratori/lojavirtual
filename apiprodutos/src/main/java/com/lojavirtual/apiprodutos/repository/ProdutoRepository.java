package com.lojavirtual.apiprodutos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.lojavirtual.apiprodutos.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
	@Transactional
    @Modifying(clearAutomatically = true)
	@Query("update Produto p set p.estoqueDisponivel = :estoqueDisponivel where p = :produto")
	void adicionarEstoque(int estoqueDisponivel, Produto produto);
	
	@Transactional
    @Modifying(clearAutomatically = true)
	@Query("update Produto p set p.estoqueReservado = :estoqueReservado where p = :produto")
	void reservarEstoque(int estoqueReservado, Produto produto);
	
	@Transactional
    @Modifying(clearAutomatically = true)
	@Query("update Produto p set p.estoqueVendido = :estoqueVendido where p = :produto")
	void venderEstoque(int estoqueVendido, Produto produto);

}
