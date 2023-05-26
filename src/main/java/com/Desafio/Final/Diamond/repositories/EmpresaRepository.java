package com.Desafio.Final.Diamond.repositories;

import com.Desafio.Final.Diamond.models.EmpresaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<EmpresaModel, Integer> {
}
