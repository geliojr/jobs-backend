package com.ghost.perfilProfissional.services;

import com.ghost.perfilProfissional.models.Contato;
import com.ghost.perfilProfissional.models.Profissional;
import com.ghost.perfilProfissional.models.enums.TipoCargo;
import com.ghost.perfilProfissional.repositories.ProfissionalRepository;
import com.ghost.perfilProfissional.services.exceptions.DataIntegrityException;
import com.ghost.perfilProfissional.services.exceptions.ObjectNotFoundException;
import com.ghost.perfilProfissional.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class ProfissionalService {
    @Autowired
    ContatoService contatoService;
    @Autowired
    ProfissionalRepository profissionalRepository;

    //Serviço para encontrar profissional pelo id
    public Profissional findById(Integer id){
        Optional<Profissional> obj = profissionalRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Profissional não encontrado! Id: " + id + ", Tipo: " + Profissional.class.getSimpleName()));

    }

    //Serviço para criar profissional
    public Profissional insert(Profissional profissional){
        if (profissional.getCreateDate() == null){
            profissional.setCreateDate(new Date());
        }
        profissional.setId(null);
        profissional = profissionalRepository.save(profissional);
        if (profissional == null){
            profissional = new Profissional();
        }
        if (profissional.getContatos() != null){
            for(Contato x : profissional.getContatos()){
                Contato con = new Contato();
                con.setId(null);
                con.setNome(x.getNome());
                con.setContato(x.getContato());
                con.setProfissional(profissional);
                contatoService.insert(con);
            }
        }
            return profissional;
    }

    //Serviço para atualizar profissional pelo id
    public Profissional update(Profissional profissional){
        Profissional obj = findById(profissional.getId());
        profissional.setNome((profissional.getNome() != null) ? profissional.getNome() : obj.getNome());
        profissional.setCargo((profissional.getCargo() != null) ? profissional.getCargo() : obj.getCargo());
        profissional.setNascimento((profissional.getNascimento() != null) ? profissional.getNascimento() : obj.getNascimento());
        profissional.setCreateDate((profissional.getCreateDate() != null) ? profissional.getCreateDate() : obj.getCreateDate());
        return profissionalRepository.save(profissional);
    }

    //Serviço para remover profissional
    public void deleteById(Integer id){
        findById(id);
        try {
            profissionalRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Erro ao excluir profissional id: "+ id);
        }
    }

    //Serviço para pesquisa geral ou por campos
    //q = pesquisa simples, id, nome e profissional para pesquisa especifica
    public Page<Profissional> search(String q, Integer id, String nome, TipoCargo cargo, String nascimento, Pageable pageable) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date nasc = sdf.parse(nascimento);

        if (q.length() > 0) {
            if (Utils.isEnumTipoCargo(q)) {
                return profissionalRepository.findAllByCargo(TipoCargo.toCod(q), pageable);
            } else if (Utils.isIntegerNumber(q)) {
                return profissionalRepository.findAllById(Integer.parseInt(q), pageable);
            } else if (Utils.isValidStringDate(q)) {
                return profissionalRepository.findAllByNascimentoOrCreateDate(sdf.parse(q),sdf.parse(q), pageable);
            } else {
                return profissionalRepository.findAllByNomeIgnoreCase(q, pageable);
            }
        } else if (cargo != null) {
            return profissionalRepository.findAllByIdOrNomeIgnoreCaseOrCargoOrNascimento(id, nome, cargo.getCod(),nasc, pageable);
        } else {
            return profissionalRepository.findAllByIdOrNomeIgnoreCaseOrNascimento(id, nome, nasc, pageable);
        }
    }

}



