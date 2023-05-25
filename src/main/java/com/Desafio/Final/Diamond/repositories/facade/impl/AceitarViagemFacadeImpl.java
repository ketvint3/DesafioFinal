package com.Desafio.Final.Diamond.repositories.facade.impl;

import com.Desafio.Final.Diamond.models.MotoristaModel;
import com.Desafio.Final.Diamond.models.ViagemModel;
import com.Desafio.Final.Diamond.models.enu.ViagemEnum;
import com.Desafio.Final.Diamond.repositories.facade.AceitarViagemFacade;
import com.Desafio.Final.Diamond.services.MotoristaService;

import com.Desafio.Final.Diamond.services.ViagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AceitarViagemFacadeImpl implements AceitarViagemFacade {
    @Autowired
    private ViagemService viagemService;
    @Autowired
    private MotoristaService motoristaService;

    @Override
    public boolean aceitarViagem(Integer codigo, Integer codigoMotorista) {

        ViagemModel viagemModel = viagemService.buscarCodigo(codigo);
        MotoristaModel motoristaModel = motoristaService.buscarCodigo(codigoMotorista);

        if (viagemModel != null && motoristaModel != null) {
            viagemModel.setStatusViagem(ViagemEnum.EM_ANDAMENTO);
            viagemModel.setMotorista(motoristaModel);

            viagemService.update(codigo, viagemModel);
            return true;

        }
        return false;
    }
}
