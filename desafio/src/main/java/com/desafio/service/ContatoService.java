package com.desafio.service;

import com.desafio.model.Contato;
import com.desafio.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;

    public Contato salvarContato(Contato contato){
        return contatoRepository.save(contato);
    }

    public void deleteContatoById(Long id){
        contatoRepository.deleteById(id);
    }

    public List<Contato> findAll(){
        return contatoRepository.findAll();
    }

    public Contato findById(Long id) {
        try {
            return contatoRepository.findById(id).get();
        } catch(NoSuchElementException e) {
            throw new EntityNotFoundException("EntityNotFoundException Contato id: " + id);
        }
    }
    public Contato update(Long id, Contato contato) {
        Contato contatoEntity = contatoRepository.getReferenceById(id);

        contatoEntity.setContato(contato.getContato());
        contatoEntity.setProfissional(contato.getProfissional());
        contatoEntity.setNome(contato.getNome());

        return contatoRepository.save(contatoEntity);
    }

    public List<Contato> findContatosByParametros(String contato, String nome) {

        try {
            return contatoRepository.findContatosByParametros(contato,nome);
        } catch(NoSuchElementException e) {
            throw new EntityNotFoundException("EntityNotFoundException Profissional");
        }

    }
}
