package com.Desafio.Final.Diamond.services;

import com.Desafio.Final.Diamond.models.SuporteModel;
import com.Desafio.Final.Diamond.repositories.SuporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SuporteService {

    @Autowired
    private SuporteRepository suporteRepository;

    public void cadastrar(SuporteModel suporte) {

        suporteRepository.save(suporte);
    }

    public List<SuporteModel> listar() {

        return suporteRepository.findAll();
    }

    public void update(Integer codigo, SuporteModel suporte) {
        if (suporteRepository.existsById(codigo)) {
            suporteRepository.save(suporte);
        }
    }

    public SuporteModel listarCodigo(Integer codigo) {

        Optional<SuporteModel> optSuporte = suporteRepository.findById(codigo);
        if (optSuporte.isEmpty()) {

            return null;
        }
        return optSuporte.get();
    }

    public void remover(Integer codigo) {

        if (suporteRepository.existsById(codigo)){
            suporteRepository.deleteById(codigo);
        }
    }
}
