package com.Desafio.Final.Diamond.Serveces;

import com.Desafio.Final.Diamond.Models.SuporteModel;
import com.Desafio.Final.Diamond.Repositorys.SuporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SuporteService {
    @Autowired
    private SuporteRepository suporteRepository;

    //ADICIONANDO
    public void adicionar(SuporteModel suporteSalvo) {
        suporteRepository.save(suporteSalvo);

    }

    //LISTANDO

    public List<SuporteModel> listar() {
        return suporteRepository.findAll();


    }

    //PROCURANDO
    public Optional<SuporteModel> acharPorCodigo(Integer codigo) {
        return suporteRepository.findById(codigo);

    }

    //ATUALIZANDO
    public void update(Integer codigo, SuporteModel suporte) {
        if (suporteRepository.existsById(codigo)) {
            suporteRepository.save(suporte);


            //REMOVENDO
            if (suporteRepository.existsById(codigo)) {
                suporteRepository.deleteById(codigo);
            }
        }
    }

    public void remove(Integer codigo) {
    }
}
