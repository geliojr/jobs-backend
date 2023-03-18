package com.desafio.controller;

import com.desafio.model.Contato;
import com.desafio.model.Profissional;
import com.desafio.service.ContatoService;
import io.swagger.annotations.Api;
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

    @GetMapping(value = "/contacts")
    public ResponseEntity<List<Contato>> getContatosByParametros(@RequestParam("contato") String contato, @RequestParam("nome") String nome){

        List<Contato> contatos = contatoService.findContatosByParametros(contato,nome);
        return ResponseEntity.ok().body(contatos);
    }

    @GetMapping(value = "/contacts/{id}")
    public ResponseEntity<Contato> findById(@PathVariable Long id){

        Contato contato = contatoService.findById(id);
        return ResponseEntity.ok().body(contato);
    }

    /*@GetMapping(value = "/all-contacts")
    public ResponseEntity<List<Contato>> findAll(){

        List<Contato> contatos = contatoService.findAll();
        return ResponseEntity.ok().body(contatos);
    }

    public ResponseEntity<Contato> findById(@PathVariable Long id){
        Contato contato = contatoService.findById(id);
        return ResponseEntity.ok().body(contato);
    }*/

    @PostMapping(value = "/contacts")
    public ResponseEntity<String> save(@RequestBody Contato contato){
        Contato contatoSaved = contatoService.salvarContato(contato);

        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/contatos/{id}")
                .buildAndExpand(contatoSaved.getId()).toUri();

        return ResponseEntity.ok("Sucesso contato cadastrado.");

        //return ResponseEntity.created(uri).body(contatoSaved);
    }

    @DeleteMapping(value = "/contacts/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        contatoService.deleteContatoById(id);
        return ResponseEntity.ok("Sucesso contato excluído.");
    }


    @PutMapping(value = "/contacts/{id}")
    public ResponseEntity<Contato> update(@PathVariable Long id, @RequestBody Contato contato){
        Contato contatoAtualizado = contatoService.update(id, contato);

        return ResponseEntity.ok().body(contatoAtualizado);
    }

}