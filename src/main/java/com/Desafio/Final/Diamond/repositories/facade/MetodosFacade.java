package com.Desafio.Final.Diamond.repositories.facade;

import com.Desafio.Final.Diamond.models.MotoristaModel;
import com.Desafio.Final.Diamond.models.PagamentoModel;
import com.Desafio.Final.Diamond.models.ValorModel;
import org.springframework.stereotype.Repository;

@Repository
public interface MetodosFacade {

    MotoristaModel buscarCodigoDeMotorista(Integer motoristaId);

    ValorModel buscarCodigoDeValor(Integer valorId);

    void salvarPagamento(PagamentoModel pagamentoModel);
}
