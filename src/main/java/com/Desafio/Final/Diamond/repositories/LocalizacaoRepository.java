package com.Desafio.Final.Diamond.repositories;

import com.Desafio.Final.Diamond.models.LocalizacaoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalizacaoRepository extends JpaRepository<LocalizacaoModel, Integer> {
}
