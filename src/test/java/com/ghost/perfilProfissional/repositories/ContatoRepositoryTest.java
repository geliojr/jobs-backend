package com.ghost.perfilProfissional.repositories;

import com.ghost.perfilProfissional.models.Contato;
import com.ghost.perfilProfissional.models.Profissional;
import com.ghost.perfilProfissional.models.enums.TipoCargo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
class ContatoRepositoryTest {

    ContatoRepositoryTest() throws ParseException {
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }
    
    @Autowired
    private ContatoRepository contatoRepository;

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
            return Sort.sort(Contato.class);
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

    Profissional profissional = new Profissional(null, "Pedro Santos", TipoCargo.DESENVOLVEDOR, sdf.parse("25/04/1968"), new Date());
    Contato contato = new Contato(null, "Juliana Rh", "82991234855", profissional);

    @Test
    void testSave() {
        Contato contato1 = contatoRepository.save(contato);
        assertThat(contato1).isNotNull();
    }

    @Test
    void testFindById(){
        Optional<Contato> contato = contatoRepository.findById(1);
        assertThat(contato).isNotNull();
    }

    @Test
    void testFindAll(){
        List<Contato> contato = contatoRepository.findAll();
        assertThat(contato).isNotNull();
    }

    @Test
    void testFindAllById(){
        Page<Contato> con = contatoRepository.findAllById(1, pageable);
        assertThat(con).isNotNull();
        assertThat(con).isEqualTo(con);
    }

    @Test
    void testFindAllByNome(){
        Page<Contato> con = contatoRepository.findAllByNome("Juliana Rh", pageable);
        assertThat(con).isNotNull();
        assertThat(con).isEqualTo(con);
    }

    @Test
    void testFindAllByContato(){
        Page<Contato> con = contatoRepository.findAllByContato("82991234855", pageable);
        assertThat(con).isNotNull();
        assertThat(con).isEqualTo(con);
    }

    @Test
    void testSearchAll(){
        Page<Contato> con = contatoRepository.searchAll("Juliana Rh", pageable);
        assertThat(con).isNotNull();
        assertThat(con).isEqualTo(con);
    }


}