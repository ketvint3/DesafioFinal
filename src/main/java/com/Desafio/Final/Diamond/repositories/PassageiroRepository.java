package com.Desafio.Final.Diamond.repositories;

import com.Desafio.Final.Diamond.models.PassageiroModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassageiroRepository extends JpaRepository<PassageiroModel, Integer> {
    PassageiroModel findByEmail(String email);
}
