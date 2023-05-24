package com.Desafio.Final.Diamond.facade;

import org.springframework.stereotype.Repository;

@Repository
public interface AceitarViagemFacade {
    boolean aceitarViagem(Integer codigo, Integer codigoMotorista);
}




