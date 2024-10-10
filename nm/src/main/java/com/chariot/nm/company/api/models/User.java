package com.chariot.nm.company.api.models;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String senha;
    private String cep;

    public User() {
        // no-arg constructor
    }

    public User(Long id, String nome, String senha, String cep) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.cep = cep;
    }

    public User(String nome, String senha, String cep) {
        this.nome = nome;
        this.senha = senha;
        this.cep = cep;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public boolean isValid() {
        // Verificar se os dados do usuário são válidos
        if (this.getNome() == null || this.getNome().isEmpty()) {
            return false;
        }
        if (this.getSenha() == null || this.getSenha().isEmpty()) {
            return false;
        }
        if (this.getCep() == null || this.getCep().isEmpty()) {
            return false;
        }
        return true;
    }
}