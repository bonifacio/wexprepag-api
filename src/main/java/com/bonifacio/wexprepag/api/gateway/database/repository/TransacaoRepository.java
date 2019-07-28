package com.bonifacio.wexprepag.api.gateway.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bonifacio.wexprepag.api.gateway.database.entity.Venda;

public interface TransacaoRepository extends JpaRepository<Venda, Long> {

}
