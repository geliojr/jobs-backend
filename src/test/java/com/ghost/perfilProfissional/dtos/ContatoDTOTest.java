package com.ghost.perfilProfissional.dtos;

import com.ghost.perfilProfissional.dtos.converter.ContatoDTOConverter;
import com.ghost.perfilProfissional.models.Contato;
import com.ghost.perfilProfissional.models.Profissional;
import com.ghost.perfilProfissional.models.enums.TipoCargo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ContatoDTOTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testModelContato() {
        Profissional profissional = new Profissional(1, "Pedro", TipoCargo.DESENVOLVEDOR, new Date("12/11/1992"), new Date("12/11/2021"));
        Contato contato = new Contato(1, "Juliana Rh", "82991234855", profissional);
        ContatoDTO dto = ContatoDTOConverter.entityToDTO(contato);

        assertThat(dto)
                .isNotNull()
                .satisfies(u -> {
                    assertThat(u.getId()).isEqualTo(1);
                    assertThat(u.getNome()).isEqualTo("Juliana Rh");
                    assertThat(u.getContato()).isEqualTo("82991234855");
                    assertThat(u.getIdProfissional()).isEqualTo(contato.getProfissional().getId());
                });
    }
}