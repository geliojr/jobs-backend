package com.ghost.perfilProfissional.models;

import com.ghost.perfilProfissional.models.enums.TipoCargo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ContatoTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Test
    void testModelContato() throws ParseException {
        Profissional profissional = new Profissional(1, "Pedro", TipoCargo.DESENVOLVEDOR, new Date("12/11/1992"), new Date("12/11/2021"));
        Contato contato = new Contato(1, "Juliana Rh", "82991234855", profissional);

        assertThat(contato)
                .isNotNull()
                .satisfies(u -> {
                    assertThat(u.getId()).isEqualTo(1);
                    assertThat(u.getNome()).isEqualTo("Juliana Rh");
                    assertThat(u.getContato()).isEqualTo("82991234855");
                    assertThat(u.getProfissional()).isEqualTo(profissional);

                });
    }
}