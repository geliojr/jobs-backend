package com.ghost.perfilProfissional.services;

import com.ghost.perfilProfissional.models.Contato;
import com.ghost.perfilProfissional.models.Profissional;
import com.ghost.perfilProfissional.models.enums.TipoCargo;
import com.ghost.perfilProfissional.repositories.ContatoRepository;
import com.ghost.perfilProfissional.repositories.ProfissionalRepository;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProfissionalServiceTest {

    ProfissionalServiceTest() throws ParseException {
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @InjectMocks
    ProfissionalService profissionalService;

    @Mock
    ProfissionalRepository profissionalRepository;

    @InjectMocks
    ContatoService contatoService;

    @Mock
    ContatoRepository contatoRepository;

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

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    Profissional profissional = new Profissional(1, "Pedro Santos", TipoCargo.DESENVOLVEDOR, sdf.parse("25/04/1968"), sdf.parse("25/04/1968"));

    @Test
    public void testFindById() {

        when(profissionalRepository.findById(1)).thenReturn(java.util.Optional.of(profissional));

        Profissional con = profissionalService.findById(1);
        assertEquals(1, con.getId());
        assertEquals(profissional.getNome(), con.getNome());
        assertEquals(profissional.getCargo(), con.getCargo());
        assertEquals(profissional.getNascimento(), con.getNascimento());

    }

    @Test
    public void testSearch() throws ParseException {
        List<Profissional> list = new ArrayList<>();

        list.add(profissional);
        //test
        Page<Profissional> con = profissionalService.search("", null, "Pedro Santos", null, "1/01/1800", pageable);
        when(profissionalRepository.findAllByNomeIgnoreCase("Pedro Santos", pageable)).thenReturn(con);

        assertThat(con, CoreMatchers.hasItems());
        assertThat(con, CoreMatchers.is(con));
    }

//    da erro pois dentro do profissional pega o retorno do repository, porem o teste nao consegue fazer isso.
    @Test
    public void testInsert() throws ParseException {
        Profissional profissional1 = new Profissional(1, "Pedro Santos", TipoCargo.DESENVOLVEDOR, sdf.parse("25/04/1968"), sdf.parse("25/04/1968"));
        Contato contato = new Contato(null, "Juliana Rh", "82991234855", profissional);
        profissional1.getContatos().addAll(Arrays.asList(contato));
        profissionalService.insert(profissional1);
        verify(profissionalRepository, times(1)).save(profissional1);
    }


}