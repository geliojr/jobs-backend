package com.ghost.perfilProfissional.dtos;

import com.ghost.perfilProfissional.models.Contato;

import java.io.Serializable;

public class ContatoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;
    private String contato;
    private Integer idProfissional;

    public ContatoDTO() {
    }

    public ContatoDTO(Contato con) {
        super();
        id = con.getId();
        nome = con.getNome();
        contato = con.getContato();
        idProfissional = con.getProfissional().getId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getIdProfissional() {
        return idProfissional;
    }

    public void setIdProfissional(Integer idProfissional) {
        this.idProfissional = idProfissional;
    }
}
