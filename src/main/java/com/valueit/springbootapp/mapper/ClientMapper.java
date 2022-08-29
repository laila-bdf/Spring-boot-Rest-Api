package com.valueit.springbootapp.mapper;

import com.valueit.springbootapp.dto.ClientDTO;
import com.valueit.springbootapp.exception.EnableToMapNullEntityException;
import com.valueit.springbootapp.exception.EntityNotFoundException;
import com.valueit.springbootapp.model.Client;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;


@Component
public class ClientMapper {

    @Autowired
    private  ModelMapper modelMapper;

    public Client toEntity(ClientDTO clientDTO){
        if(clientDTO==null){
            throw new EnableToMapNullEntityException("clientDto shouldn't be null");
        }
        return modelMapper.map(clientDTO ,Client.class);
    }

    public  ClientDTO toDTO(Client client) {
        if(client==null){
            throw new EnableToMapNullEntityException("client shouldn't be null");
        }
        return modelMapper.map(client ,ClientDTO.class);
    }

    public Page<ClientDTO> toDTO(Page<Client> clientPage) {
        return clientPage.map(client -> toDTO(client));
    }
}
