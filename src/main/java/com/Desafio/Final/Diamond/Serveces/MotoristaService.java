package com.Desafio.Final.Diamond.Serveces;

import com.Desafio.Final.Diamond.Models.MotoristaModel;
import com.Desafio.Final.Diamond.Repositorys.MotoristaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MotoristaService {

    @Autowired
    private MotoristaRepository repository;

    public void add(MotoristaModel novoMotorista) {
        repository.save(novoMotorista);
    }

    public List<MotoristaModel> listarMotoristas() {
        return repository.findAll();
    }

    public void update(Integer codigo, MotoristaModel motorista) {
        if (repository.existsById(codigo)) {
            repository.save(motorista);
        }
    }

    public void remove(Integer codigo) {
        if (repository.existsById(codigo)) {
            repository.deleteById(codigo);
        }
    }

    public MotoristaModel pesquisarMotoristaPorId(Integer codigo) {

        Optional<MotoristaModel> optionalMotoristaModel = repository.findById(codigo);
        if (optionalMotoristaModel.isEmpty()) {
            return null;
        }
        return optionalMotoristaModel.get();
    }
}
