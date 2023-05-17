package com.Desafio.Final.Diamond.Passageiro.Controller;


import java.util.ArrayList;
import java.util.List;

import com.Desafio.Final.Diamond.Passageiro.Models.Passageiro;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/passageiros")
public class PassageiroController {
    private List<Passageiro> passageiros;

    public PassageiroController() {
        passageiros = new ArrayList<>();
    }

    @GetMapping
    public ResponseEntity<List<Passageiro>> listarPassageiros() {
        return ResponseEntity.ok(passageiros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Passageiro> buscarPassageiro(@PathVariable String id) {
        Passageiro passageiro = encontrarPassageiro(id);
        if (passageiro != null) {
            return ResponseEntity.ok(passageiro);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> adicionarPassageiro(@RequestBody Passageiro passageiro) {
        passageiros.add(passageiro);
        return ResponseEntity.status(HttpStatus.CREATED).body("Passageiro adicionado com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarPassageiro(@PathVariable String id, @RequestBody Passageiro novoPassageiro) {
        Passageiro passageiro = encontrarPassageiro(id);
        if (passageiro != null) {
            passageiro.setNome(novoPassageiro.getNome());
            passageiro.setEndereco(novoPassageiro.getEndereco());
            return ResponseEntity.ok("Passageiro atualizado com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removerPassageiro(@PathVariable String id) {
        Passageiro passageiro = encontrarPassageiro(id);
        if (passageiro != null) {
            passageiros.remove(passageiro);
            return ResponseEntity.ok("Passageiro removido com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private Passageiro encontrarPassageiro(String id) {
        for (Passageiro passageiro : passageiros) {
            if (passageiro.getId().equals(id)) {
                return passageiro;
            }
        }
        return null;
    }
}








