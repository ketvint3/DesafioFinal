package com.Desafio.Final.Diamond.services;

import com.Desafio.Final.Diamond.models.MotoristaModel;
import com.Desafio.Final.Diamond.models.ViagemModel;
import com.Desafio.Final.Diamond.repositories.MotoristaRepository;
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
    public MotoristaModel listarCodigo(Integer codigo) {

        Optional<MotoristaModel> optMotorista = repository.findById(codigo);
        if (optMotorista.isEmpty()) {

            return null;
        }
        return optMotorista.get();
    }

    public void update(Integer id, MotoristaModel motorista) {
        if (repository.existsById(id)) {
            repository.save(motorista);
        }
    }

    public void remove(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }

    public MotoristaModel pesquisarMotoristaPorId(Integer id) {

        Optional<MotoristaModel> optionalMotoristaModel = repository.findById(id);
        if (optionalMotoristaModel.isEmpty()) {
            return null;
        }
        return optionalMotoristaModel.get();
    }
}
