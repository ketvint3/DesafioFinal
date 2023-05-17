package com.Desafio.Final.Diamond.Repository;

import com.Desafio.Final.Diamond.Models.PagamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<PagamentoModel, Integer> {
}
