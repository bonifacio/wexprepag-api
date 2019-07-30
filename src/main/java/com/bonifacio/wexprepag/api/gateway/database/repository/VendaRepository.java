package com.bonifacio.wexprepag.api.gateway.database.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bonifacio.wexprepag.api.gateway.database.entity.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long> {

	@Query(" from #{#entityName} where cartao.id = :cartao ")
	List<Venda> findByCartao(@Param("cartao") String cartao);
}
