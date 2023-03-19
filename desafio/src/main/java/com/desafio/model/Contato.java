package com.desafio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String contato;

    @ManyToOne
    @JoinColumn(name = "profissional_id")

    private Profissional profissional;

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }
}
