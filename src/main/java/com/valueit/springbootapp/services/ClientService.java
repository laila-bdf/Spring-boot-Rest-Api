package com.valueit.springbootapp.services;

import java.util.Date;
import java.util.Optional;

import com.valueit.springbootapp.services.specification.ClientSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.valueit.springbootapp.dto.ClientDTO;
import com.valueit.springbootapp.exception.EntityNotFoundException;
import com.valueit.springbootapp.mapper.ClientMapper;
import com.valueit.springbootapp.model.Client;
import com.valueit.springbootapp.repository.ClientRepository;

@Service
@Slf4j
public class ClientService {

    @Autowired
    private ClientRepository clientrepository;

    @Autowired
    private ClientMapper clientMapper;

    public Page<ClientDTO> findAll(Pageable page) {
        log.info("Getting page {} of {} clients ", page.getPageNumber(), page.getPageSize());
        return clientMapper.toDTO(clientrepository.findAll(page));
    }

    public Page<ClientDTO> findBy(String nom, String prenom, Pageable page) {
        log.info("Getting page {} of {} clients using filter : {} , {} ", page.getPageNumber(),page.getPageSize(),nom,prenom);
        //return clientMapper.toDTO(clientrepository.findClientByNomIgnoreCaseAndAndPrenomIgnoreCase(nom,prenom,page));
        return  clientMapper.toDTO(clientrepository.findAll(
                ClientSpecification.findByNom(nom)
                        .and(ClientSpecification.findByPrenom(prenom)),page));
    }

    public ClientDTO findById(Long id) {
        log.info("Getting client with id {}", id);
        Optional<Client> oClient = clientrepository.findById(id);
        if (oClient.isPresent()) {
            return clientMapper.toDTO(oClient.get());
        } else {
            log.error("Client with id [{}] not found", id);
            throw new EntityNotFoundException("client not found");
        }
    }

    public ClientDTO save(ClientDTO client) {
        return clientMapper.toDTO(clientrepository.save(clientMapper.toEntity(client)));
    }

    public ClientDTO update(ClientDTO client, Long id) {

        findById(id);
        client.setId(id);
        log.info("Client with id [{}] updated",id);
        return clientMapper.toDTO(clientrepository.save(clientMapper.toEntity(client)));

    }

    public String deleteClientById(Long id) {
        findById(id);
        clientrepository.deleteById(id);
        log.info("Client with id [{}] deleted",id);
        return "Done";
    }
    public void deleteAll(){
        clientrepository.deleteAll();
    }

}