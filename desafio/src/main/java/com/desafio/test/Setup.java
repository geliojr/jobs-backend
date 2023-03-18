package com.desafio.test;

import com.desafio.enums.Cargo;
import com.desafio.model.Contato;
import com.desafio.model.Profissional;
import com.desafio.service.ContatoService;
import com.desafio.service.ProfissionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class Setup implements CommandLineRunner {

    @Autowired
    private ContatoService contatoService;

    @Autowired
    private ProfissionalService profissionalService;


    @Override
    public void run(String... args) throws Exception {

        // cadastro profissional 1
        Profissional profissional1 = new Profissional();

        profissional1.setNome("Eduardo Leonel");
        profissional1.setCreateData(new Date());
        profissional1.setNascimento(new Date());


        Cargo cargo = Cargo.DESENVOLVEDOR;
        profissional1.setCargo(cargo.getDescricao());

        profissionalService.save(profissional1);

        // cadastro contatos do profissional 1
        Contato contato1 = new Contato();

        contato1.setNome("Eduarda");
        contato1.setContato("41 989992132");
        contato1.setProfissional(profissional1);

        contatoService.salvarContato(contato1);

        Contato contato2 = new Contato();

        contato2.setNome("Maria");
        contato2.setContato("54 998472910");
        contato2.setProfissional(profissional1);

        contatoService.salvarContato(contato2);

        // cadastro profissional 2
        Profissional profissional2 = new Profissional();

        profissional2.setNome("Julio Cesar");
        profissional2.setCreateData(new Date());
        profissional2.setNascimento(new Date());

        Cargo cargo2 = Cargo.TESTER;
        profissional2.setCargo(cargo2.getDescricao());

        profissionalService.save(profissional2);

        // cadastro contatos do profissional 2

        Contato contato3 = new Contato();

        contato3.setNome("Arnaldo");
        contato3.setContato("31 954992214");
        contato3.setProfissional(profissional2);

        contatoService.salvarContato(contato3);

        Contato contato4 = new Contato();

        contato4.setNome("Fernando");
        contato4.setContato("44 952521325");
        contato4.setProfissional(profissional2);

        contatoService.salvarContato(contato4);

    }
}
