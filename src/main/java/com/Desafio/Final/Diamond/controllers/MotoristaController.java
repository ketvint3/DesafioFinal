package com.Desafio.Final.Diamond.controllers;

import com.Desafio.Final.Diamond.models.MotoristaModel;
import com.Desafio.Final.Diamond.repositories.MotoristaRepository;
import com.Desafio.Final.Diamond.repositories.util.FileUploadUtil;
import com.Desafio.Final.Diamond.services.MotoristaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/motorista")
public class MotoristaController {

    @Autowired
    private MotoristaService service;
    @Autowired
    private MotoristaRepository repository;

    @PostMapping(value = "/cadastrar")
    @Operation(summary = "Cadastra motoristas", description = "Método da api para cadastro de motoristas na plataforma")
    @ApiResponse(responseCode = "200", description = "Operação concluida com sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity cadastrar(@Valid @RequestBody MotoristaModel codigo) {

        try {
            service.adicionar(codigo);
            return new ResponseEntity("Motorista cadastrado com sucesso!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Não foi possivel cadastrar o motorista! Tente novamente.", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{id}/adicionarfoto")
    @Operation(summary = "Salva imagem de perfil do motorista", description = "Método da api para cadastrar imagem de perfil do motoca")
    @ApiResponse(responseCode = "200", description = "Operação concluida com sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")
    public ResponseEntity salvarImagemMotorista(MotoristaModel motorista,
                                              @RequestParam("Image") MultipartFile multipartfile) throws IOException {
        try {


            String fileName = StringUtils.cleanPath(multipartfile.getOriginalFilename());
            motorista.setPhotos(fileName);

            MotoristaModel fotoMotorista = repository.save(motorista);

            String uploadDir = "motorista-photos/" + fotoMotorista.getCodigo();

            FileUploadUtil.saveFile(uploadDir, fileName, multipartfile);
            return new ResponseEntity("Imagem cadastrada com sucesso!", HttpStatus.OK);
        }catch (IOException e){
            return new ResponseEntity("Não foi possivel cadastrar a imagem!Tente novamente.",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/listar")
    @Operation(summary = "Lista motoristas", description = "Método da api para listagem de todos os motoristas cadastrados no banco.")
    @ApiResponse(responseCode = "200", description = "Operação concluida com sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity listar() {

        return new ResponseEntity(service.listar(), HttpStatus.OK);
    }

    @GetMapping(value = "/listar/{id}")
    @Operation(summary = "Lista motoristas por código", description = "Faz a listagem do motorista referente ao código informado")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity listarPorCodigo(@PathVariable Integer id) {

        try {
            return new ResponseEntity(service.buscarCodigo(id), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Código Inválido!", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/alterar/{id}")
    @Operation(summary = "Altera o motorista", description = "Método da api para alterar os dados de um motorista")
    @ApiResponse(responseCode = "200", description = "Operação concluida com sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity alterar(@PathVariable Integer codigo, @RequestBody MotoristaModel motorista) {
        try {
            service.update(codigo, motorista);
            return new ResponseEntity("Motorista alterado com sucesso!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Não foi possivel alterar o perfil de motorista! Tente novamente", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/deletar/{id}")
    @Operation(summary = "Deleta motorista", description = "Método da api para exclusão de um motorista da plataforma")
    @ApiResponse(responseCode = "200", description = "Operação concluida com sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")
    public ResponseEntity deletar(@PathVariable Integer codigo) {
        try {
            service.remover(codigo);
            return new ResponseEntity("Motorista do código" + codigo + "foi removido com sucesso!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Código invalido!", HttpStatus.BAD_REQUEST);
        }
    }

}
