package com.ghost.perfilProfissional.repositories;

import com.ghost.perfilProfissional.models.Contato;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Integer> {
    //Pesquisa por id
    @Transactional(readOnly=true)
    Page<Contato> findAllById(Integer id, Pageable pageable);
    //Pesquisa por nome
    @Transactional(readOnly=true)
    @Query("SELECT obj FROM Contato obj WHERE LOWER(obj.nome) LIKE LOWER(concat('%' ,?1, '%'))")
    Page<Contato> findAllByNome(String nome, Pageable pageable);
    //Pesquisa por contato
    @Transactional(readOnly=true)
    @Query("SELECT obj FROM Contato obj WHERE LOWER(obj.contato) LIKE LOWER(concat(?1, '%'))")
    Page<Contato> findAllByContato(String contato, Pageable pageable);

    //Realiza a pesquisa procurando por qualquer correspondencia na tabela.
    @Transactional(readOnly=true)
    @Query("SELECT obj FROM Contato obj WHERE LOWER(obj.nome) LIKE LOWER(CONCAT('%',:q,'%')) OR LOWER(obj.contato) LIKE LOWER(CONCAT('%',:q,'%'))")
    Page<Contato> searchAll(String q, Pageable pageable);

    //Realiza a pesquisa procurando por qualquer correspondencia na tabela.
    @Transactional(readOnly=true)
    @Query("SELECT obj FROM Contato obj WHERE obj.id = :id OR LOWER(obj.nome) LIKE LOWER(CONCAT('%',:q,'%')) OR LOWER(obj.contato) LIKE LOWER(CONCAT('%',:q,'%'))")
    Page<Contato> searchAllWithId(Integer id, String q, Pageable pageable);
}
