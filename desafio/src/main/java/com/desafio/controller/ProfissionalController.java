package com.desafio.controller;

import com.desafio.enums.Cargo;
import com.desafio.model.Contato;
import com.desafio.model.Profissional;
import com.desafio.service.ContatoService;
import com.desafio.service.ProfissionalService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@Api(value = "API REST Desafio")
public class ProfissionalController {

    @Autowired
    private ProfissionalService profissionalService;

    @Autowired
    private ContatoService contatoService;

    @GetMapping(value = "/professionals/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){

        try {
            Profissional profissional = profissionalService.findById(id);
            return ResponseEntity.ok().body(profissional);
        }

        catch(Exception e){
            return ResponseEntity.unprocessableEntity().body("Profissional id: " +id +" não cadastrado no sistema");
        }

    }

    @DeleteMapping(value = "/professionals/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){

        try{
            Profissional profissional = profissionalService.findById(id);

            for(int i=0;i<profissional.getContatos().size();i++){
                contatoService.deleteContatoById(profissional.getContatos().get(i).getId());
            }

            profissionalService.deleteProfissionalById(id);
            return ResponseEntity.ok("Sucesso profissional excluído.");
        }

        catch(Exception e){
            return ResponseEntity.unprocessableEntity().body("Profissional id: " +id +" não cadastrado no sistema");
        }


    }

    @PostMapping(value = "/professionals")
    public ResponseEntity<String> save(@RequestBody Profissional profissional){

        if(profissional!=null){
            int result = 0;

            result = Cargo.findByNumber(profissional.getCargo());

            if(result!=0){
                profissionalService.save(profissional);

                return ResponseEntity.ok("Sucesso profissional cadastrado.");
            }

            else{
                return ResponseEntity.unprocessableEntity().body("Cargo id: " +profissional.getCargo() +" não cadastrado no sistema");
            }

        }

        return ResponseEntity.unprocessableEntity().body("Dados inválidos");

    }

    @PutMapping(value = "/professionals/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Profissional profissional){

        try {
            if (profissional != null) {
                int result = 0;

                result = Cargo.findByNumber(profissional.getCargo());

                if (result != 0) {
                    Profissional profissionalAtualizado = profissionalService.update(id, profissional);
                    return ResponseEntity.ok().body(profissionalAtualizado);
                } else {
                    return ResponseEntity.unprocessableEntity().body("Cargo id: " + profissional.getCargo() + " não cadastrado no sistema");
                }

            }
        }
        catch(Exception e){
            return ResponseEntity.unprocessableEntity().body("Profissional id: " + id + " não cadastrada no sistema");
        }
        return ResponseEntity.unprocessableEntity().body("Dados inválidos");
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

    @GetMapping(value = "/all-professionals")
    @ResponseBody
    public ResponseEntity<List<Profissional>> listarTodosOsProfissionais(){

        try{
            List<Profissional> profissionais = profissionalService.findAll();
            return ResponseEntity.ok().body(profissionais);
        }

        catch(EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }

    }

}
