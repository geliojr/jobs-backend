package com.ghost.perfilProfissional.services;

import com.ghost.perfilProfissional.models.Contato;
import com.ghost.perfilProfissional.repositories.ContatoRepository;
import com.ghost.perfilProfissional.repositories.ProfissionalRepository;
import com.ghost.perfilProfissional.services.exceptions.DataIntegrityException;
import com.ghost.perfilProfissional.services.exceptions.ObjectNotFoundException;
import com.ghost.perfilProfissional.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContatoService {
    @Autowired
    ContatoRepository contatoRepository;
    @Autowired
    ProfissionalRepository profissionalRepository;

    //Serviço para encontrar contato pelo id
    public Contato findById(Integer id){
        Optional<Contato> obj = contatoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Contato não encontrado! Id: " + id + ", Tipo: " + Contato.class.getSimpleName()));

    }

    //Serviço para criar contato
    public Contato insert(Contato contato){
        contato.setId(null);
        return contatoRepository.save(contato);
    }

    //Serviço para atualizar contato pelo id
    public Contato update(Contato contato){
        Contato obj = findById(contato.getId());
        contato.setNome((contato.getNome() != null) ? contato.getNome() : obj.getNome());
        contato.setContato((contato.getContato() != null) ? contato.getContato() : obj.getContato());
        contato.setProfissional((contato.getProfissional().getId() != null) ? contato.getProfissional() : obj.getProfissional());
        return contatoRepository.save(contato);
    }

    //Serviço para remover contato
    public void deleteById(Integer id){
        findById(id);
        try {
            contatoRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Erro ao excluir contato id: "+ id);
        }
    }

    public Page<Contato> search(String q, Integer id, String nome, String contato, Pageable pageable) {
        if(id != null || nome.length() > 0 || contato.length() > 0){
            if (id != null ){
                return contatoRepository.findAllById(id, pageable);
            }else if(contato.length() > 0){
                return contatoRepository.findAllByContato(contato, pageable);
            }else{
                return contatoRepository.findAllByNome(nome, pageable);
            }
        }
        else if (q.length() > 0){
            if (Utils.isIntegerNumber(q)){
                Integer qid = Integer.valueOf(q);
                return contatoRepository.searchAllWithId(qid, q, pageable);
            }else {
                return contatoRepository.searchAll(q, pageable);
            }
        }else{
            return contatoRepository.findAll(pageable);
        }
    }
}
