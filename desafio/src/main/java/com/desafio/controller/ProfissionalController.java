package com.desafio.controller;

import com.desafio.model.Profissional;
import com.desafio.service.ContatoService;
import com.desafio.service.ProfissionalService;
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
public class ProfissionalController {

    @Autowired
    private ProfissionalService profissionalService;

    @Autowired
    private ContatoService contatoService;

    @GetMapping(value = "/professionals/{id}")
    public ResponseEntity<Profissional> findById(@PathVariable Long id){

        Profissional profissional = profissionalService.findById(id);
        return ResponseEntity.ok().body(profissional);
    }


    @DeleteMapping(value = "/professionals/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){

        Profissional profissional = profissionalService.findById(id);

        for(int i=0;i<profissional.getContatos().size();i++){
            contatoService.deleteContatoById(profissional.getContatos().get(i).getId());
        }

        profissionalService.deleteProfissionalById(id);
        return ResponseEntity.ok("Sucesso profissional excluído.");
    }

    @PostMapping(value = "/professionals")
    public ResponseEntity<String> save(@RequestBody Profissional profissional){
        Profissional profissionalSaved = profissionalService.save(profissional);

        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/professionals/{id}")
                .buildAndExpand(profissionalSaved.getId()).toUri();

        return ResponseEntity.ok("Sucesso profissional cadastrado.");

        //return ResponseEntity.created(uri).body(profissionalSaved);
    }

    @PutMapping(value = "/professionals/{id}")
    public ResponseEntity<Profissional> update(@PathVariable Long id, @RequestBody Profissional profissional){

        Profissional profissionalAtualizado = profissionalService.update(id, profissional);

        return ResponseEntity.ok().body(profissionalAtualizado);
    }

    @GetMapping(value = "/professionals")
    @ResponseBody
    public ResponseEntity<List<Profissional>> listarProfissionaisPorParametrosPassados(@RequestParam("cargo") int cargo, @RequestParam("create_data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  Date createData, @RequestParam("nome") String nome,  @RequestParam("nascimento") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date nascimento){

        try{
            List<Profissional> profissionais = profissionalService.findProfissionaisByParametros(cargo,createData,nome,nascimento);
            return ResponseEntity.ok().body(profissionais);
        }

        catch(EntityNotFoundException e){
           return ResponseEntity.notFound().build();
        }

    }



    /*@GetMapping(value = "/all-professionals")
    @ResponseBody
    public ResponseEntity<List<Profissional>> listarTodosOsProfissionais(){

        try{
            List<Profissional> profissionais = profissionalService.findAll();
            return ResponseEntity.ok().body(profissionais);
        }

        catch(EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }

    }*/

}
