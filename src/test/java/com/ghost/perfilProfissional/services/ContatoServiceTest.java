package com.ghost.perfilProfissional.services;

import com.ghost.perfilProfissional.models.Contato;
import com.ghost.perfilProfissional.models.Profissional;
import com.ghost.perfilProfissional.models.enums.TipoCargo;
import com.ghost.perfilProfissional.repositories.ContatoRepository;
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
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
class ContatoServiceTest {

    ContatoServiceTest() throws ParseException {
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

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

    Profissional profissional = new Profissional(null, "Pedro Santos", TipoCargo.DESENVOLVEDOR, sdf.parse("25/04/1968"), new Date());
    Contato contato = new Contato(1, "Juliana Rh", "82991234855", profissional);

    @Test
    public void testFindById() {

        when(contatoRepository.findById(1)).thenReturn(java.util.Optional.of(contato));

        Contato con = contatoService.findById(1);
        assertEquals(1, con.getId());
        assertEquals("Juliana Rh", con.getNome());
        assertEquals("82991234855", con.getContato());
        assertEquals(profissional, con.getProfissional());

    }

    @Test
    public void testSearch(){
        List<Contato> list = new ArrayList<>();
        Contato contato = new Contato(null, "Juliana Rh", "82991234855", profissional);

        list.add(contato);
        //test
        Page<Contato> con = contatoService.search("", null, "Juliana Rh", "", pageable);
        when(contatoRepository.searchAll("Juliana Rh", pageable)).thenReturn(con);

        assertThat(con, CoreMatchers.hasItems());
        assertThat(con, CoreMatchers.is(con));
    }

    @Test
    public void testInsert(){
        contatoService.insert(contato);
        verify(contatoRepository, times(1)).save(contato);
    }

}