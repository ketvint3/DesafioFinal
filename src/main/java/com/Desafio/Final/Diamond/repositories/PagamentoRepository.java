package com.Desafio.Final.Diamond.repositories;

import com.Desafio.Final.Diamond.models.PagamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<PagamentoModel, Integer> {
}
