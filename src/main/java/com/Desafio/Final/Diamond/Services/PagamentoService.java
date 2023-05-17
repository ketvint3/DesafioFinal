package com.Desafio.Final.Diamond.Services;

import com.Desafio.Final.Diamond.Models.PagamentoModel;
import com.Desafio.Final.Diamond.Repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PagamentoService {
    @Autowired
    private PagamentoRepository pagamentoRepository;

    public PagamentoService(PagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }

    public PagamentoModel criarPagamento(String taxa, String valor, LocalizacaoModel localizacao) {
        PagamentoModel pagamento = new PagamentoModel();
        pagamento.setTaxa(taxa);
        pagamento.setValor(valor);
        pagamento.setLocalizacao(localizacao);


        pagamentoRepository.save(pagamento);

        return pagamento;
    }

    public List<PagamentoModel> listarPagamentos() {
        return pagamentoRepository.findAll();
    }

    public PagamentoModel buscarPagamentoPorId(Integer id) {
        return pagamentoRepository.findById(id).orElse(null);
    }

    public void atualizarPagamento(Integer id, String novaTaxa, String novoValor) {
        PagamentoModel pagamento = pagamentoRepository.findById(id).orElse(null);
        if (pagamento != null) {
            pagamento.setTaxa(novaTaxa);
            pagamento.setValor(novoValor);
            pagamentoRepository.save(pagamento);
        }
    }

    public void excluirPagamento(Integer id) {
        pagamentoRepository.deleteById(id);
    }
}

