package com.Desafio.Final.Diamond.repositories.facade;

import org.springframework.stereotype.Repository;

@Repository
public interface AceitarViagemFacade {
    boolean aceitarViagem(Integer codigo, Integer codigoMotorista);
}




