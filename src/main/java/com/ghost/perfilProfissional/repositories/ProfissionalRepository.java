package com.ghost.perfilProfissional.repositories;

import com.ghost.perfilProfissional.models.Contato;
import com.ghost.perfilProfissional.models.Profissional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface ProfissionalRepository extends JpaRepository<Profissional, Integer>{

    //Lista de consultas que realiza a pesquisa procurando por qualquer correspondencia na tabela.
    @Transactional(readOnly=true)
    Page<Profissional> findAllById(Integer id, Pageable pageable);
    @Transactional(readOnly=true)
    Page<Profissional> findAllByCargo(Integer cargo, Pageable pageable);
    @Transactional(readOnly=true)
    Page<Profissional> findAllByNascimentoOrCreateDate(Date nascimento, Date createdDate, Pageable pageable);
    @Transactional(readOnly=true)
    Page<Profissional> findAllByNomeIgnoreCase(String nome, Pageable pageable);

    //Pesquisa por id, nome, cargo ou nascimento
    @Transactional(readOnly=true)
    Page<Profissional> findAllByIdOrNomeIgnoreCaseOrCargoOrNascimento(Integer id, String nome, Integer cargo, Date nascimento, Pageable pageable);

    //Pesquisa por id, nome ou nascimento
    @Transactional(readOnly=true)
    Page<Profissional> findAllByIdOrNomeIgnoreCaseOrNascimento(Integer id, String nome, Date nascimento, Pageable pageable);

}
