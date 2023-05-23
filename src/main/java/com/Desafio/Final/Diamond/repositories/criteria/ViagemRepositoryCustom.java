package com.Desafio.Final.Diamond.repositories.criteria;

import com.Desafio.Final.Diamond.models.ViagemModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViagemRepositoryCustom {

    List<ViagemModel> listarPendentes();
}
