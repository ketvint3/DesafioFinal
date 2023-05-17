package com.Desafio.Final.Diamond.controllers;

import com.Desafio.Final.Diamond.models.PassageiroModel;
import com.Desafio.Final.Diamond.services.PassageiroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/passageiro")
public class PassageiroController {

    @Autowired
    private PassageiroService passageiroService;

    @GetMapping(value = "/listar")
    @Operation(summary = "Listar passageiros", description = "Método da api para listagem de todos os passageiros cadastrados no banco.")
    @ApiResponse(responseCode = "200", description = "Operação concluida com sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity<List<PassageiroModel>> listarPassageiros() {
        return new ResponseEntity<>(passageiroService.listarPassageiros(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Listar código do passageiro", description = "Método da api para listagem do passageiro, conforme seu código informado na plataforma")
    @ApiResponse(responseCode = "200", description = "Operação concluida com sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity<PassageiroModel> buscarPassageiro(@PathVariable Integer id) {
        PassageiroModel passageiro = encontrarPassageiro(id);
        if (passageiro != null) {
            return ResponseEntity.ok(passageiro);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/cadastrar")
    @Operation(summary = "Cadastrar passageiros", description = "Método da api para cadastro de passageiro na plataforma")
    @ApiResponse(responseCode = "200", description = "Operação concluida com sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity<String> adicionarPassageiro(@RequestBody PassageiroModel passageiro) {
        passageiroService.adicionarPassageiro(passageiro);
        return ResponseEntity.status(HttpStatus.CREATED).body("Passageiro adicionado com sucesso!");
    }

    @PutMapping(value = "/alterar/{id}")
    @Operation(summary = "Atualizar passageiro", description = "Método da api para alterar os dados de um passageiro")
    @ApiResponse(responseCode = "200", description = "Operação concluida com sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

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

    @DeleteMapping(value = "/deletar/{id}")
    @Operation(summary = "Deletar passageiro", description = "Método da api para exclusão de um passageiro da plataforma")
    @ApiResponse(responseCode = "200", description = "Operação concluida com sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity<String> removerPassageiro(@PathVariable Integer id) {
        PassageiroModel passageiro = encontrarPassageiro(id);
        if (passageiro != null) {
            passageiroService.removerPassageiro(id);
            return ResponseEntity.ok("Passageiro removido com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}/recusar-viagem")
    public ResponseEntity<Void> recusarViagem(@PathVariable Integer id) {
        PassageiroModel passageiro = passageiroService.buscarPassageiro(id);

        if (passageiro != null) {
            passageiro.setRecusada(String.valueOf(true));
            passageiroService.atualizarPassageiro(id, passageiro);
            return ResponseEntity.ok().build(); // Viagem recusada com sucesso
        } else {
            return ResponseEntity.notFound().build(); // Não foi possível encontrar o passageiro com o ID fornecido
        }
    }
    private PassageiroModel encontrarPassageiro(Integer id) {

       return passageiroService.buscarPassageiro(id);
    }
}
