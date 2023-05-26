package com.Desafio.Final.Diamond.facade;

import com.Desafio.Final.Diamond.models.PagamentoModel;

public interface FinalizarViagemFacade {

    boolean finalizarViagem(Integer codigo, PagamentoModel pagamento, Integer codigoPagamento);
}
