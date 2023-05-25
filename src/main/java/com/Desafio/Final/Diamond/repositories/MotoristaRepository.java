package com.Desafio.Final.Diamond.repositories;

import com.Desafio.Final.Diamond.models.MotoristaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MotoristaRepository extends JpaRepository<MotoristaModel, Integer> {

    MotoristaModel findByEmail(String email);
}
