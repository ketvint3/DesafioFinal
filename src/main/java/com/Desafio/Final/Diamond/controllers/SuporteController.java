package com.Desafio.Final.Diamond.controllers;

import com.Desafio.Final.Diamond.models.LocalizacaoModel;
import com.Desafio.Final.Diamond.models.SuporteModel;
import com.Desafio.Final.Diamond.repositories.SuporteRepository;
import com.Desafio.Final.Diamond.services.LocalizacaoService;
import com.Desafio.Final.Diamond.services.SuporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/suporte")
public class SuporteController {


    @Autowired
        private SuporteService service;

        @GetMapping(value = "/listar")
        public ResponseEntity listarPorCodigo(){
            return new ResponseEntity(service.listar(), HttpStatus.OK);
        }

        @PostMapping(value = "/add")
        public ResponseEntity suporteAdicionado (@RequestBody SuporteModel suporte){
            service.adicionar(suporte);
            return new ResponseEntity(suporte, HttpStatus.CREATED);
        }
        @PutMapping(value = "/alterar/{codigo}")
        public ResponseEntity alterar(@PathVariable Integer codigo ,
                                      @RequestBody SuporteModel suporte){
            service.update(codigo, suporte);
            return new ResponseEntity(suporte, HttpStatus.OK);
        }
        @DeleteMapping(value = "/deletar/{codigo}")
        public ResponseEntity deletar(@PathVariable Integer codigo){
            service.remove(codigo);
            return new ResponseEntity(HttpStatus.OK);
        }



    }


