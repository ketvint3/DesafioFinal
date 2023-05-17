package com.Desafio.Final.Diamond.Controllers;

import com.Desafio.Final.Diamond.Models.PagamentoModel;
import com.Desafio.Final.Diamond.Services.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(value = "/Pagamento")
public class PagamentoController {
    @Autowired
    private PagamentoService pagamentoService;
    @Autowired
    private  LocalizacaoService localizacaoService;

    public PagamentoController(PagamentoService pagamentoService, LocalizacaoService localizacaoService) {
        this.pagamentoService = pagamentoService;
        this.localizacaoService = localizacaoService;
    }

    @PostMapping(value = "/criar")
    public PagamentoModel criarPagamento(@RequestParam String taxa, @RequestParam String valor, @RequestBody LocalizacaoModel localizacao) {
        return pagamentoService.criarPagamento(taxa, valor, localizacao);
    }

    @GetMapping(value = "/listar")
    public List<PagamentoModel> listarPagamentos() {
        return pagamentoService.listarPagamentos();
    }

    @GetMapping(value = "/buscar/{id}")
    public PagamentoModel buscarPagamentoPorId(@PathVariable Integer id) {
        return pagamentoService.buscarPagamentoPorId(id);
    }

    @PutMapping(value = "/atualizar/{id}")
    public void atualizarPagamento(@PathVariable Integer id, @RequestParam String novaTaxa, @RequestParam String novoValor) {
        pagamentoService.atualizarPagamento(id, novaTaxa, novoValor);
    }

    @DeleteMapping(value = "/excluir/{id}")
    public void excluirPagamento(@PathVariable Integer id) {
        pagamentoService.excluirPagamento(id);
    }
}

