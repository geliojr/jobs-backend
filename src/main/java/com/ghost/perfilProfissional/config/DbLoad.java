package com.ghost.perfilProfissional.config;

import com.ghost.perfilProfissional.models.Contato;
import com.ghost.perfilProfissional.models.Profissional;
import com.ghost.perfilProfissional.models.enums.TipoCargo;
import com.ghost.perfilProfissional.repositories.ContatoRepository;
import com.ghost.perfilProfissional.repositories.ProfissionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@Profile({"test", "dev"})
@Configuration
public class DbLoad implements CommandLineRunner {
    @Autowired
    ProfissionalRepository profissionalRepository;
    @Autowired
    ContatoRepository contatoRepository;

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public void run(String... args) throws Exception {

        Profissional profissional = new Profissional(null, "Pedro Santos", TipoCargo.DESENVOLVEDOR, sdf.parse("25/04/1968"), new Date());
        Profissional profissional2 = new Profissional(null, "Jaqueline Maria", TipoCargo.DESIGNER, sdf.parse("28/07/1993"), new Date());
        Profissional profissional3 = new Profissional(null, "Pedro Antonio", TipoCargo.SUPORTE, sdf.parse("02/03/1995"), new Date());

        //profissional1
        Contato contato1 = new Contato(null, "Juliana Rh", "82991234855", profissional);
        Contato contato2 = new Contato(null, "Henrique Juliano", "82998563344", profissional);
        Contato contato3 = new Contato(null, "Mariana Silva", "82988945678", profissional);
        Contato contato4 = new Contato(null, "Anna Contabilidade", "82990908876", profissional);
        Contato contato5 = new Contato(null, "Felipe Dev", "82988967432", profissional);
        Contato contato6 = new Contato(null, "Pedro Henrique", "82995634855", profissional);
        Contato contato7 = new Contato(null, "Pedro Lanches", "82494794558", profissional);
        Contato contato8 = new Contato(null, "Pedro Augusto", "82091012109", profissional);
        Contato contato9 = new Contato(null, "Marcio Daniel", "82023412167", profissional);
        profissional.getContatos().addAll(Arrays.asList(contato1, contato2, contato3, contato4, contato5, contato6, contato7, contato8, contato9));

        //profissional2
        Contato contato10 = new Contato(null, "Alice Clara", "82787395332", profissional2);
        Contato contato11 = new Contato(null, "Bryan Vitor", "82598061533", profissional2);
        Contato contato12 = new Contato(null, "Melissa Maria ", "82536647968", profissional2);
        Contato contato13 = new Contato(null, "Helena Lavínia", "82557287820", profissional2);
        profissional2.getContatos().addAll(Arrays.asList(contato10, contato11, contato12, contato13));

        //profissional3
        Contato contato14 = new Contato(null, "Ana Rafaela", "82251168321", profissional3);
        Contato contato15 = new Contato(null, "Noah Cunha", "82861535321", profissional3);
        profissional3.getContatos().addAll(Arrays.asList(contato14, contato15));


        profissionalRepository.saveAll(Arrays.asList(profissional, profissional2, profissional3));
        contatoRepository.saveAll(Arrays.asList(contato1, contato2, contato3, contato4, contato5,
                                                contato6, contato7, contato8, contato9, contato10,
                                                contato11,contato12,contato13,contato14,contato15));

    }
}
