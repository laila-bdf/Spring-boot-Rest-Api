package com.valueit.springbootapp.services;

import com.valueit.springbootapp.dto.CommandeDTO;
import com.valueit.springbootapp.exception.EntityNotFoundException;
import com.valueit.springbootapp.exception.UnableToSaveEntityException;
import com.valueit.springbootapp.mapper.CommandeMapper;
import com.valueit.springbootapp.model.Commande;
import com.valueit.springbootapp.repository.CommandeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CommandeService {

    public static Logger log = LoggerFactory.getLogger(CommandeService.class);

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private ClientService clientService;
    @Autowired
    private CommandeMapper commandeMapper;

    public Page<CommandeDTO> findAll(Pageable page) {
        log.info("Getting page {} of {} commandes ", page.getPageNumber(), page.getPageSize());
        return commandeMapper.toDTO(commandeRepository.findAll(page));
    }

    public CommandeDTO findById(Long id) {
        log.info("Getting commande with id {}", id);
        Optional<Commande> oCommande = commandeRepository.findById(id);
        if (oCommande.isPresent()) {
            return commandeMapper.toDTO(oCommande.get());
        } else {
            log.error("Commande with id [ {} ] not found", id);
            throw new EntityNotFoundException("Commande not found");
        }
    }

    public CommandeDTO save(CommandeDTO commande) {
        clientService.findById(commande.getClient().getId());
        try {
            return commandeMapper.toDTO(commandeRepository.save(commandeMapper.toEntity(commande)));
        }catch (Exception e){
            throw new UnableToSaveEntityException("unable to save Commande");
        }

    }

    public CommandeDTO update(CommandeDTO commande, Long id) {
        findById(id);
        commande.setId(id);
        log.info("Commande with id [{}] updated",id);
        return commandeMapper.toDTO(commandeRepository.save(commandeMapper.toEntity(commande)));

    }

    public String deleteCommandeById(Long id) {
        findById(id);
        commandeRepository.deleteById(id);
        log.info("Commande with id [{}] deleted",id);
        return "Done";
    }

    public Page<CommandeDTO> getCommandesByClientId(long id , Pageable page) {
        return commandeMapper.toDTO(commandeRepository.getCommandeByClientId(id , page));
    }

}
