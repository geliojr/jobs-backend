package com.desafio.service;

import com.desafio.model.Contato;
import com.desafio.model.Profissional;
import com.desafio.repository.ContatoRepository;
import com.desafio.repository.ProfissionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProfissionalService {

    @Autowired
    private ProfissionalRepository profissionalRepository;

    @Autowired
    private ContatoRepository contatoRepository;

    public Profissional save(Profissional profissional){

        profissionalRepository.save(profissional);
        return profissional;
    }

    public List<Profissional> findAll(){
        return profissionalRepository.findAll();
    }

    public void deleteProfissionalById(Long id){
        profissionalRepository.deleteById(id);
    }

    public Profissional findById(Long id) {
        try {
            return profissionalRepository.findById(id).get();
        } catch(NoSuchElementException e) {
            throw new EntityNotFoundException("EntityNotFoundException Profissional id: " + id);
        }
    }

    public Profissional update(Long id, Profissional profissional) {

        Profissional profissionalEntity = profissionalRepository.getReferenceById(id);

        profissionalEntity.setNascimento(profissional.getNascimento());
        profissionalEntity.setCreateData(profissional.getCreateData());
        profissionalEntity.setNome(profissional.getNome());
        profissionalEntity.setCargo(profissional.getCargo());

        return profissionalRepository.save(profissionalEntity);
    }

    public List<Profissional> findProfissionaisByParametros(int cargo,  Date createData, String nome, Date nascimento){

        try {
            return profissionalRepository.findProfissionaisByParametros(cargo, createData, nome, nascimento);
        } catch(NoSuchElementException e) {
            throw new EntityNotFoundException("EntityNotFoundException Profissional");
        }

    }

}
