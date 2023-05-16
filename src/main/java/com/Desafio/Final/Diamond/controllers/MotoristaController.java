package com.Desafio.Final.Diamond.controllers;

import com.Desafio.Final.Diamond.models.MotoristaModel;
import com.Desafio.Final.Diamond.services.MotoristaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/motorista")
public class MotoristaController {

    @Autowired
    private MotoristaService service;

    @GetMapping(value = "/motoristas")
    @Operation(summary = "Listar Motoristas", description = "Método da api para listagem de todos os motoristas cadastrados no banco.")
    @ApiResponse(responseCode = "200", description = "Operação concluida com sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")
    public ResponseEntity listarMotoristas() {
        return new ResponseEntity(service.listarMotoristas(), HttpStatus.OK);
    }

    @PostMapping(value = "/motoristas/cadastrar")
    @Operation(summary = "Cadastrar motoristas", description = "Método da api para cadastro de motoristas na plataforma")
    @ApiResponse(responseCode = "200", description = "Operação concluida com sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")
    public ResponseEntity cadastrarMotorista(@RequestBody MotoristaModel id) {

        try {
            service.add(id);
            return new ResponseEntity("Motorista cadastrado com sucesso!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Não foi possivel cadastrar o motorista!Tente novamente.", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/motoristas/deletar/{id}")
    @Operation(summary = "Deletar motorista", description = "Método da api para exclusão de um motorista da plataforma")
    @ApiResponse(responseCode = "200", description = "Operação concluida com sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")
    public ResponseEntity excluirMotorista(@PathVariable Integer id) {
        try {
            service.remove(id);
            return new ResponseEntity("Excluido com sucesso!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Id invalido!", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/motoristas/{id}")
    @Operation(summary = "Atualizar motorista", description = "Método da api para alterar os dados de um motorista")
    @ApiResponse(responseCode = "200", description = "Operação concluida com sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")
    public ResponseEntity atualizarMotorista(Integer id, MotoristaModel motorista) {
        try {
            service.update(id, motorista);
            return new ResponseEntity("Motorista alterado com sucesso!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Não foi possivel alterar o perfil de motorista!Tente novamente", HttpStatus.BAD_REQUEST);
        }
    }
}
