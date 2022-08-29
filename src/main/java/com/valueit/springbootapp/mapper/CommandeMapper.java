package com.valueit.springbootapp.mapper;

import com.valueit.springbootapp.dto.CommandeDTO;
import com.valueit.springbootapp.exception.EnableToMapNullEntityException;
import com.valueit.springbootapp.model.Commande;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class CommandeMapper {
    @Autowired
    private ModelMapper modelMapper;



    public Commande toEntity(CommandeDTO cmdDTO){
        if(cmdDTO==null){
            throw new EnableToMapNullEntityException("cmdDTO shouldn't be null");
        }
        return modelMapper.map(cmdDTO ,Commande.class);
    }

    public  CommandeDTO toDTO(Commande commande) {
        if(commande==null){
            throw new EnableToMapNullEntityException("commande shouldn't be null");
        }
        return modelMapper.map(commande ,CommandeDTO.class);
    }

    public Page<CommandeDTO> toDTO(Page<Commande> commandePage) {
        return commandePage.map(cmd -> toDTO(cmd));
    }

}
