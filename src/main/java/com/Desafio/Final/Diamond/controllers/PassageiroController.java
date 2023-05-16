package com.Desafio.Final.Diamond.controllers;

import com.Desafio.Final.Diamond.models.PassageiroModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/passageiro")
public class PassageiroController {

    private List<PassageiroModel> passageiros;

    public PassageiroController() {
        passageiros = new ArrayList<>();
    }

    @GetMapping
    public ResponseEntity<List<PassageiroModel>> listarPassageiros() {
        return ResponseEntity.ok(passageiros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PassageiroModel> buscarPassageiro(@PathVariable Integer id) {
        PassageiroModel passageiro = encontrarPassageiro(id);
        if (passageiro != null) {
            return ResponseEntity.ok(passageiro);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> adicionarPassageiro(@RequestBody PassageiroModel passageiro) {
        passageiros.add(passageiro);
        return ResponseEntity.status(HttpStatus.CREATED).body("Passageiro adicionado com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarPassageiro(@PathVariable Integer id, @RequestBody PassageiroModel novoPassageiro) {
        PassageiroModel passageiro = encontrarPassageiro(id);
        if (passageiro != null) {
            passageiro.setNome(novoPassageiro.getNome());
            passageiro.setEndereco(novoPassageiro.getEndereco());
            return ResponseEntity.ok("Passageiro atualizado com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removerPassageiro(@PathVariable Integer id) {
        PassageiroModel passageiro = encontrarPassageiro(id);
        if (passageiro != null) {
            passageiros.remove(passageiro);
            return ResponseEntity.ok("Passageiro removido com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private PassageiroModel encontrarPassageiro(Integer id) {
        for (PassageiroModel passageiro : passageiros) {
            if (passageiro.getCodigo().equals(id)) {
                return passageiro;
            }
        }
        return null;
    }
}
