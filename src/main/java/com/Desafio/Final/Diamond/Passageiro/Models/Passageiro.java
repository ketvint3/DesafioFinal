package com.Desafio.Final.Diamond.Passageiro.Models;

public class Passageiro {
    private String id;
    private String nome;
    private String endereco;

    public Passageiro() {
        // Construtor vazio necessário para deserialização JSON
    }

    public Passageiro(String id, String nome, String endereco) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
