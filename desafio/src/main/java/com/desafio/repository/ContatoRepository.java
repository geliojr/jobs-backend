package com.desafio.repository;

import com.desafio.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContatoRepository extends JpaRepository<Contato, Long> {

    @Query("SELECT c FROM Contato c WHERE contato = ?1 or nome = ?2")
    List<Contato> findContatosByParametros(@Param("contato") String contato, @Param("nome") String nome);

}
