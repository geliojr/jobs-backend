package com.ghost.perfilProfissional.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ghost.perfilProfissional.models.Contato;
import com.ghost.perfilProfissional.models.Contato;
import com.ghost.perfilProfissional.models.Profissional;
import com.ghost.perfilProfissional.models.enums.TipoCargo;
import com.ghost.perfilProfissional.repositories.ContatoRepository;
import com.ghost.perfilProfissional.services.ContatoService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ContatoController.class)
class ContatoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContatoService contatoService;
    @MockBean
    private ContatoRepository contatoRepository;

    @InjectMocks
    private ContatoController contatoController;

    ContatoControllerTest() throws ParseException {
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

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
    Contato contato = new Contato(1, "Juliana Rh", "82991234855", profissional);

    @Test
    void testFindAll() throws Exception {

        List<Contato> proList = Arrays.asList(contato, contato);
        Page<Contato> proPage = new PageImpl<>(proList, pageable, proList.size());

        given(contatoRepository.findAllByNome(contato.getNome(), pageable)).willReturn(proPage);

        mockMvc.perform(get("/contacts/fields?nome=Juliana Rh").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void testFindById() throws Exception {
        when(contatoService.findById(1)).thenReturn(contato);
        mockMvc.perform( MockMvcRequestBuilders
                .get("/contacts/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(contato.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value(contato.getNome()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.contato").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.contato").isNotEmpty());
    }

    @Test
    public void testInsert() throws Exception{
        when(contatoService.insert(any())).thenReturn(contato);
        mockMvc.perform(post("/contacts").
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(contato))).
                andExpect(status().isCreated());
        verify(contatoService,times(1)).insert(any());
    }

    @Test
    public void testDeleteById() throws Exception {
        mockMvc.perform(delete("/contacts/{id}", 1))
                .andExpect(status().isNoContent());
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}