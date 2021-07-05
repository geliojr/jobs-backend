package com.ghost.perfilProfissional.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ghost.perfilProfissional.models.enums.TipoCargo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Profissional implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private Integer cargo;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date nascimento;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date createDate;

//    @OneToMany(mappedBy = "profissional",cascade = CascadeType.ALL, orphanRemoval = true)
    @OneToMany(mappedBy = "profissional", orphanRemoval = true)
    private List<Contato> contatos = new ArrayList<>();

    public Profissional() {
    }

    public Profissional(Integer id, String nome, TipoCargo cargo, Date nascimento, Date createDate) {
        this.id = id;
        this.nome = nome;
        this.cargo = cargo.getCod();
        this.nascimento = nascimento;
        this.createDate = createDate;
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

    public TipoCargo getCargo() {
        return TipoCargo.toEnum(cargo);
    }

    public void setCargo(TipoCargo cargo) {
        this.cargo = cargo.getCod();
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profissional that = (Profissional) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Profissional{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cargo=" + cargo +
                ", nascimento=" + nascimento +
                ", createDate=" + createDate +
                ", contatos=" + contatos +
                '}';
    }
}
