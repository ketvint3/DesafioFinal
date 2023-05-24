package com.Desafio.Final.Diamond.services;

import com.Desafio.Final.Diamond.models.AvaliacaoModel;
import com.Desafio.Final.Diamond.repositories.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.Desafio.Final.Diamond.models.enu.AvaliacaoEnum.*;

@Service
public class AvaliacaoService {
    @Autowired
    public AvaliacaoRepository avaliacaoRepository;

    //ADICIONANDO
    public void adicionar(AvaliacaoModel avaliacao) {
        AvaliacaoModel model = new AvaliacaoModel();
        switch (avaliacao.getAvaliacao()) {

            case PESSIMO:
                model.setAvaliacao(PESSIMO);
                break;

            case RUIM:
                model.setAvaliacao(RUIM);
                break;

            case BOM:
                model.setAvaliacao(BOM);
                break;

            case RAZOAVEL:
                model.setAvaliacao(RAZOAVEL);
                break;

            case OTIMO:
                model.setAvaliacao(OTIMO);
                break;

        }

        avaliacaoRepository.save(model);

    }
        public List<AvaliacaoModel> listar() {

            return avaliacaoRepository.findAll();
        }

    public void update(Integer codigo, AvaliacaoModel avaliacao) {
        if (avaliacaoRepository.existsById(codigo)) {
            avaliacaoRepository.save(avaliacao);
        }
    }
    public void remover(Integer codigo) {

        if (avaliacaoRepository.existsById(codigo)) {
            avaliacaoRepository.deleteById(codigo);

        }
    }
}



