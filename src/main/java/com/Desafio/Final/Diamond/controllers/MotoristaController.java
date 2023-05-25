package com.Desafio.Final.Diamond.controllers;

import com.Desafio.Final.Diamond.models.MotoristaModel;
import com.Desafio.Final.Diamond.repositories.MotoristaRepository;
import com.Desafio.Final.Diamond.repositories.util.FileUploadUtil;
import com.Desafio.Final.Diamond.services.MotoristaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/motorista")
public class MotoristaController {
    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private MotoristaService service;
    @Autowired
    private MotoristaRepository repository;

    @PostMapping(value = "/cadastrar")
    @Operation(summary = "Cadastra motoristas", description = "Método da api para cadastro de motoristas na plataforma")
    @ApiResponse(responseCode = "200", description = "Operação concluida com sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity cadastrar(@Valid
                                    @RequestBody MotoristaModel codigo) {

        try {
            service.adicionar(codigo);
            return new ResponseEntity("Motorista cadastrado com sucesso!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Não foi possivel cadastrar o motorista! Tente novamente.", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/foto/{codigo}")
    @Operation(summary = "Salva imagem de perfil do motorista", description = "Método da api para cadastrar imagem de perfil do motoca")
    @ApiResponse(responseCode = "200", description = "Operação concluida com sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")
    public ResponseEntity salvarImagemMotorista(MotoristaModel motorista,
                                                @RequestParam("Image")
                                                MultipartFile multipartfile) throws IOException {
        try {

            String fileName = StringUtils.cleanPath(multipartfile.getOriginalFilename());
            motorista.setPhotos(fileName);

            MotoristaModel fotoMotorista = repository.save(motorista);

            String uploadDir = "motorista-photos/" + fotoMotorista.getCodigo();

            FileUploadUtil.saveFile(uploadDir, fileName, multipartfile);
            return new ResponseEntity("Imagem salva com sucesso!", HttpStatus.OK);
        } catch (IOException e){
            return new ResponseEntity("Não foi possivel salvar a imagem! Tente novamente.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/listar")
    @Operation(summary = "Lista motoristas", description = "Método da api para listagem de todos os motoristas cadastrados no banco.")
    @ApiResponse(responseCode = "200", description = "Operação concluida com sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity listar() {

        try {
            return new ResponseEntity(service.listar(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Erro! Tente novamente.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/listar/{codigo}")
    @Operation(summary = "Lista motoristas por código", description = "Faz a listagem do motorista referente ao código informado")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity listarPorCodigo(@PathVariable Integer codigo) {

        try {
            return new ResponseEntity(service.buscarCodigo(codigo), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity( "Código Inválido!", HttpStatus.NOT_FOUND);
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
            return new ResponseEntity("Erro! Tente novamente.", HttpStatus.BAD_REQUEST);
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

    @PostMapping("/senha/{email}")
    @Operation(summary = "Criar nova senha",description = "Método da api para envio de nova senha para o email do motorista")
    @ApiResponse(responseCode = "200",description = "Operação concluida com sucesso!")
    @ApiResponse(responseCode = "404",description = "Erro na operação!")
    @ApiResponse(responseCode = "500",description = "Erro inesperado!")
    @ResponseBody
    public String enviarSenha(@PathVariable String email) {
        MotoristaModel motorista = service.buscarPorEmail(email);

        if (motorista == null) {
            return "E-mail não encontrado";

        }

        String novaSenha = service.gerarNovaSenha();
        motorista.setSenha(novaSenha);
        service.adicionar(motorista);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(motorista.getEmail());
        message.setSubject("Senha de acesso");
        message.setText("Sua nova senha de acesso é: " + novaSenha);

        try {
            emailSender.send(message);
            return "Nova senha enviada com sucesso";
        } catch (MailException e) {
            return "Erro ao enviar e-mail";
        }
    }
}
