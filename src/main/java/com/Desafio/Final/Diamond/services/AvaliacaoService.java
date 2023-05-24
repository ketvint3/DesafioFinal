package com.Desafio.Final.Diamond.services;

import com.Desafio.Final.Diamond.models.AvaliacaoEnum;
import com.Desafio.Final.Diamond.models.AvaliacaoModel;
import com.Desafio.Final.Diamond.repositories.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvaliacaoService {

    @Autowired
    public AvaliacaoRepository avaliacaoRepository;


    //ADICIONANDO
    public void adicionar(AvaliacaoModel avaliacao) {
        AvaliacaoModel model = new AvaliacaoModel();
        switch (avaliacao.getAvaliacao()){
            case PESSIMO:
                model.setAvaliacao(AvaliacaoEnum.PESSIMO);

                break;

            case RUIM:
                model.setAvaliacao(AvaliacaoEnum.RUIM);
                break;

            case BOM:
                model.setAvaliacao(AvaliacaoEnum.BOM);
                break;

            case RASOAVEL:
                model.setAvaliacao(AvaliacaoEnum.RASOAVEL);
                break;

            case OTIMO:
                model.setAvaliacao(AvaliacaoEnum.OTIMO);
                break;



        }

        avaliacaoRepository.save(model);



    }
    public void listar (AvaliacaoModel avaliacao) {
        AvaliacaoModel model = new AvaliacaoModel();
        switch (avaliacao.getAvaliacao()){
            case PESSIMO:
                model.setAvaliacao(AvaliacaoEnum.PESSIMO);

                break;

            case RUIM:
                model.setAvaliacao(AvaliacaoEnum.RUIM);
                break;

            case BOM:
                model.setAvaliacao(AvaliacaoEnum.BOM);
                break;

            case RASOAVEL:
                model.setAvaliacao(AvaliacaoEnum.RASOAVEL);
                break;

            case OTIMO:
                model.setAvaliacao(AvaliacaoEnum.OTIMO);
                break;



        }

        avaliacaoRepository.save(model);



    }
    public void alterar (AvaliacaoModel avaliacao) {
        AvaliacaoModel model = new AvaliacaoModel();
        switch (avaliacao.getAvaliacao()){
            case PESSIMO:
                model.setAvaliacao(AvaliacaoEnum.PESSIMO);

                break;

            case RUIM:
                model.setAvaliacao(AvaliacaoEnum.RUIM);
                break;

            case BOM:
                model.setAvaliacao(AvaliacaoEnum.BOM);
                break;

            case RASOAVEL:
                model.setAvaliacao(AvaliacaoEnum.RASOAVEL);
                break;

            case OTIMO:
                model.setAvaliacao(AvaliacaoEnum.OTIMO);
                break;



        }

        avaliacaoRepository.save(model);



    }
    public void deletar (AvaliacaoModel avaliacao) {
        AvaliacaoModel model = new AvaliacaoModel();
        switch (avaliacao.getAvaliacao()){
            case PESSIMO:
                model.setAvaliacao(AvaliacaoEnum.PESSIMO);

                break;

            case RUIM:
                model.setAvaliacao(AvaliacaoEnum.RUIM);
                break;

            case BOM:
                model.setAvaliacao(AvaliacaoEnum.BOM);
                break;

            case RASOAVEL:
                model.setAvaliacao(AvaliacaoEnum.RASOAVEL);
                break;

            case OTIMO:
                model.setAvaliacao(AvaliacaoEnum.OTIMO);
                break;



        }

        avaliacaoRepository.save(model);



    }





    //


}















