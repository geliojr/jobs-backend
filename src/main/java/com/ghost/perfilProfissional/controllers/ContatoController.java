package com.ghost.perfilProfissional.controllers;

import com.ghost.perfilProfissional.dtos.ContatoDTO;
import com.ghost.perfilProfissional.dtos.converter.ContatoDTOConverter;
import com.ghost.perfilProfissional.models.Contato;
import com.ghost.perfilProfissional.services.ContatoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/contacts")
public class ContatoController {

    @Autowired
    ContatoService contatoService;

    //ROTA PARA PESQUISA POR PARAMETROS q PARA PESQUIA GERAL, id, nome, contato PARA PESQUISA ESPECIFICA.
    @GetMapping(value = "/fields")
    @ApiOperation(value="Retorna o profissional com base nos parametros: '?q=' para pesquisa geral, '?id=' para id, '?nome=' para nome, '?contato=' para contato")
    public ResponseEntity<Page<Contato>> searchByPesquisa(
            @RequestParam(defaultValue = "") String q,
            @RequestParam(defaultValue = "") Integer id,
            @RequestParam(defaultValue = "") String nome,
            @RequestParam(defaultValue = "") String contato ,
            Pageable pageable) {
        Page<Contato> result = contatoService.search(q, id, nome, contato, pageable);
        return ResponseEntity.ok(result);
    }

    //ROTA DE PESQUISA POR ID DO CONTATO
    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    @ApiOperation(value="Retorna o contato pelo seu id")
    public ResponseEntity<Contato> findById(@PathVariable Integer id) {
        Contato obj = contatoService.findById(id);

        return ResponseEntity.ok().body(obj);
    }

    //ROTA PARA CRIAÇÃO DE CONTATO
    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value="Cria um contato")
    public ResponseEntity<Void> insert(@RequestBody ContatoDTO dto){
        Contato contato = ContatoDTOConverter.dtoToEntity(dto);
        contato = contatoService.insert(contato);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(contato.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    //ROTA PARA EDIÇÃO DE CONTATO
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ApiOperation(value="Edita o contato pelo seu id")
    public ResponseEntity<Void> update(@RequestBody ContatoDTO dto, @PathVariable Integer id ){
        Contato obj = ContatoDTOConverter.dtoToEntity(dto);
        obj.setId(id);
        obj = contatoService.update(obj);
        return ResponseEntity.noContent().build();
    }

    //ROTA PARA DELEÇÃO DE CONTATO
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    @ApiOperation(value="Deleta o contato pelo seu id")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        contatoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
