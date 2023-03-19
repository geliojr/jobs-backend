package com.desafio.controller;

import com.desafio.model.Contato;
import com.desafio.model.Profissional;
import com.desafio.service.ContatoService;
import com.desafio.service.ProfissionalService;
import io.swagger.annotations.Api;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.util.Date;
import java.util.List;

@RestController
@Api(value = "API REST Desafio")
public class ContatoController {

    @Autowired
    private ContatoService contatoService;

    @Autowired
    private ProfissionalService profissionalService;

    @GetMapping(value = "/contacts")
    public ResponseEntity<List<Contato>> getContatosByParametros(@RequestParam("contato") String contato, @RequestParam("nome") String nome){

        List<Contato> contatos = contatoService.findContatosByParametros(contato,nome);
        return ResponseEntity.ok().body(contatos);
    }

    @GetMapping(value = "/contacts/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){

        try {
            Contato contato = contatoService.findById(id);
            return ResponseEntity.ok().body(contato);
        }
        catch(Exception e){
            return ResponseEntity.unprocessableEntity().body("Contato ID: " +id + " não encontrado no sistema");
        }
    }

    @GetMapping(value = "/all-contacts")
    public ResponseEntity<List<Contato>> findAll(){

        List<Contato> contatos = contatoService.findAll();
        return ResponseEntity.ok().body(contatos);
    }

    @PostMapping(value = "/contacts")
    public ResponseEntity<String> save(@RequestBody Contato contato){
        if(contato.getProfissional()!=null){

            try {
                contatoService.salvarContato(contato);

                URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/contatos/{id}")
                        .buildAndExpand(contato.getId()).toUri();

                return ResponseEntity.ok("Sucesso contato cadastrado.");
            }
            catch(Exception e){
                return ResponseEntity.unprocessableEntity().body("Profissional de id " +contato.getProfissional().getId() + " não cadastrado no sistema");
            }
        }
            return ResponseEntity.unprocessableEntity().body("Profissional não informado");

    }

    @DeleteMapping(value = "/contacts/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        try{
            contatoService.deleteContatoById(id);
            return ResponseEntity.ok("Sucesso contato excluído.");
        }
        catch(Exception e){
            return ResponseEntity.unprocessableEntity().body("Contato ID: " +id + " não encontrado no sistema");
        }
    }


    @PutMapping(value = "/contacts/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Contato contato){

        try {
            return ResponseEntity.ok().body(contatoService.update(id, contato));
        }
        catch(Exception e){
            return ResponseEntity.unprocessableEntity().body("Dados inválidos");
        }

    }

}