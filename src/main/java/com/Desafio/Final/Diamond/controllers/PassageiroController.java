package com.Desafio.Final.Diamond.controllers;


import com.Desafio.Final.Diamond.Email.EmailConfig;
import com.Desafio.Final.Diamond.models.PassageiroModel;
import com.Desafio.Final.Diamond.repositories.PassageiroRepository;
import com.Desafio.Final.Diamond.repositories.util.FileUploadUtil;
import com.Desafio.Final.Diamond.services.PassageiroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
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
import java.util.Properties;

@RestController
@RequestMapping(value = "/passageiro")
public class PassageiroController {
    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private PassageiroService passageiroService;
    @Autowired
    private PassageiroRepository repository;
    @PostMapping(value = "/cadastrar")
    @Operation(summary = "Cadastrar passageiros", description = "Método da api para cadastro de passageiro na plataforma")
    @ApiResponse(responseCode = "200", description = "Operação concluida com sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity cadastrar(@Valid @RequestBody PassageiroModel passageiro) {
        passageiroService.adicionarPassageiro(passageiro);
        return ResponseEntity.status(HttpStatus.CREATED).body("Passageiro adicionado com sucesso!");
    }



    @PostMapping("/{codigo}/adicionarfoto")
    @Operation(summary = "Salvar como imagem de perfil", description = "Cadastrar imagem de perfil")
    @ApiResponse(responseCode = "200", description = "Operação concluida com sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")
    public ResponseEntity salvarImagemPassageiro(@PathVariable Integer codigo, @RequestParam("Image") MultipartFile multipartfile) throws IOException {
        try {
            PassageiroModel passageiro = passageiroService.buscarCodigo(codigo);
            if (passageiro == null) {
                return new ResponseEntity("Passageiro não encontrado", HttpStatus.NOT_FOUND);
            }

            String fileName = StringUtils.cleanPath(multipartfile.getOriginalFilename());
            passageiro.setPhotos(fileName);

            PassageiroModel fotoPassageiro = repository.save(passageiro);

            String uploadDir = "Passageiro-photos/" + fotoPassageiro.getCodigo();

            FileUploadUtil.saveFile(uploadDir, fileName, multipartfile);
            return new ResponseEntity("Imagem do passageiro salva com sucesso!", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity("Não foi possível salvar a imagem do passageiro! Tente novamente.", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(value = "/listar")
    @Operation(summary = "Listar passageiros", description = "Método da api para listagem de todos os passageiros cadastrados no banco.")
    @ApiResponse(responseCode = "200", description = "Operação concluida com sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity listar() {
        return new ResponseEntity<>(passageiroService.listarPassageiros(), HttpStatus.OK);
    }

    @GetMapping(value = "/listar/{id}")
    @Operation(summary = "Listar código do passageiro", description = "Método da api para listagem do passageiro, conforme seu código informado na plataforma")
    @ApiResponse(responseCode = "200", description = "Operação concluida com sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity listarCodigo(@PathVariable Integer id) {
        PassageiroModel passageiro = passageiroService.buscarCodigo(id);
        if (passageiro != null) {
            return ResponseEntity.ok(passageiro);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/alterar/{id}")
    @Operation(summary = "Atualizar passageiro", description = "Método da api para alterar os dados de um passageiro")
    @ApiResponse(responseCode = "200", description = "Operação concluida com sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity atualizar(@PathVariable Integer codigo, @RequestBody PassageiroModel passageiro) {

        try {
            passageiroService.atualizarPassageiro(codigo, passageiro);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possível alterar", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(passageiro, HttpStatus.OK);
    }
    @PostMapping("/enviar-senha/{email}")
    @ResponseBody
    public String enviarSenha(@PathVariable String email) {
        PassageiroModel passageiro = passageiroService.buscarPorEmail(email);

        if (passageiro == null) {
            return "E-mail não encontrado";

        }

        String novaSenha = passageiroService.gerarNovaSenha();
        passageiro.setSenha(novaSenha);
        passageiroService.adicionarPassageiro(passageiro);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(passageiro.getEmail());
        message.setSubject("Senha de acesso");
        message.setText("Sua nova senha de acesso é: " + novaSenha);

        try {
            emailSender.send(message);
            return "Nova senha enviada com sucesso";
        } catch (MailException e) {
            return "Erro ao enviar e-mail";
        }
    }
    @DeleteMapping(value = "/deletar/{id}")
    @Operation(summary = "Deletar passageiro", description = "Método da api para exclusão de um passageiro da plataforma")
    @ApiResponse(responseCode = "200", description = "Operação concluida com sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity deletar(@PathVariable Integer id) {
        PassageiroModel passageiro = passageiroService.buscarCodigo(id);
        if (passageiro != null) {
            passageiroService.removerPassageiro(id);
            return ResponseEntity.ok("Passageiro removido com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
