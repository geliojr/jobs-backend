package com.ghost.perfilProfissional.repositories;

import com.ghost.perfilProfissional.models.Profissional;
import com.ghost.perfilProfissional.models.Profissional;
import com.ghost.perfilProfissional.models.enums.TipoCargo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProfissionalRepositoryTest {

    ProfissionalRepositoryTest() throws ParseException {
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }
    
    @Autowired
    ProfissionalRepository profissionalRepository;

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    Pageable pageable = new Pageable() {
        @Override
        public int getPageNumber() {
            return 0;
        }

        @Override
        public int getPageSize() {
            return 0;
        }

        @Override
        public long getOffset() {
            return 0;
        }

        @Override
        public Sort getSort() {
            return Sort.sort(Profissional.class);
        }

        @Override
        public Pageable next() {
            return null;
        }

        @Override
        public Pageable previousOrFirst() {
            return null;
        }

        @Override
        public Pageable first() {
            return null;
        }

        @Override
        public boolean hasPrevious() {
            return false;
        }
    };

    Profissional profissional = new Profissional(null, "Pedro Santos", TipoCargo.DESENVOLVEDOR, sdf.parse("25/04/1968"), sdf.parse("25/04/1968"));

    @Test
    void testSave() {
        Profissional pro = profissionalRepository.save(profissional);
        assertThat(pro).isNotNull();
    }

    @Test
    void testFindById(){
        Optional<Profissional> pro = profissionalRepository.findById(1);
        assertThat(pro).isNotNull();
    }

    @Test
    void testFindAll(){
        List<Profissional> contato = profissionalRepository.findAll();
        assertThat(contato).isNotNull();
    }

    @Test
    void testFindAllById(){
        Page<Profissional> con = profissionalRepository.findAllById(1, pageable);
        assertThat(con).isNotNull();
        assertThat(con).isEqualTo(con);
    }

    @Test
    void testFindAllByNome(){
        Page<Profissional> con = profissionalRepository.findAllByCargo(0, pageable);
        assertThat(con).isNotNull();
        assertThat(con).isEqualTo(con);
    }

    @Test
    void testFindAllByContato() throws ParseException {
        Page<Profissional> con = profissionalRepository.findAllByNascimentoOrCreateDate(sdf.parse("25/04/1968"),sdf.parse("25/04/1968"), pageable);
        assertThat(con).isNotNull();
        assertThat(con).isEqualTo(con);
    }

    @Test
    void testSearchAll(){
        Page<Profissional> con = profissionalRepository.findAllByNomeIgnoreCase("Juliana Rh", pageable);
        assertThat(con).isNotNull();
        assertThat(con).isEqualTo(con);
    }

    @Test
    void testFindAllByIdOrNomeIgnoreCaseOrCargoOrNascimentol(){
        Page<Profissional> con = profissionalRepository.findAllByIdOrNomeIgnoreCaseOrCargoOrNascimento(profissional.getId(), profissional.getNome(), profissional.getCargo().getCod(), profissional.getNascimento(), pageable);
        assertThat(con).isNotNull();
        assertThat(con).isEqualTo(con);
    }

    @Test
    void testFindAllByIdOrNomeIgnoreCaseOrNascimento(){
        Page<Profissional> con = profissionalRepository.findAllByIdOrNomeIgnoreCaseOrNascimento(profissional.getId(), profissional.getNome(), profissional.getNascimento(), pageable);
        assertThat(con).isNotNull();
        assertThat(con).isEqualTo(con);
    }

}