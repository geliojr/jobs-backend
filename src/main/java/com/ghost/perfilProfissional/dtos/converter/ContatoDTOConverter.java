package com.ghost.perfilProfissional.dtos.converter;

import com.ghost.perfilProfissional.dtos.ContatoDTO;
import com.ghost.perfilProfissional.models.Contato;

import java.util.List;
import java.util.stream.Collectors;

public class ContatoDTOConverter {
    public static ContatoDTO entityToDTO(Contato con){
        ContatoDTO dto = new ContatoDTO();
        dto.setId(con.getId());
        dto.setNome(con.getNome());
        dto.setContato(con.getContato());
        dto.setIdProfissional(con.getProfissional().getId());
        return dto;
    }

    public static List<ContatoDTO> entityToDTOList(List<Contato> con){

        return con.stream().map(x -> entityToDTO(x)).collect(Collectors.toList());
    }

    public static Contato dtoToEntity(ContatoDTO dto){
        Contato contato = new Contato();
        contato.setId(dto.getId());
        contato.setNome(dto.getNome());
        contato.setContato(dto.getContato());
        contato.getProfissional().setId(dto.getIdProfissional());
        return contato;
    }
}
