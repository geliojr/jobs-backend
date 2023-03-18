package com.desafio.repository;

import com.desafio.model.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {

    @Query("SELECT p FROM Profissional p WHERE cargo = ?1 or create_data = ?2 or nome = ?3 or nascimento = ?4")
    List<Profissional> findProfissionaisByParametros(@Param("cargo")int cargo, @Param("create_data")Date createData, @Param("nome")String nome, @Param("nascimento") Date nascimento);

}
