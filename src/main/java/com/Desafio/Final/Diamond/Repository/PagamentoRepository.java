package com.Desafio.Final.Diamond.Repositorys;

import com.Desafio.Final.Diamond.Models.PagamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<PagamentoModel, Integer> {
}
