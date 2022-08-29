package com.valueit.springbootapp.controller;
import com.valueit.springbootapp.dto.CommandeDTO;
import com.valueit.springbootapp.model.Commande;
import com.valueit.springbootapp.services.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commandes")
public class CommandeController {
    @Autowired
    private CommandeService commandeService;

    @GetMapping
    public ResponseEntity<Page<CommandeDTO>> findAll(Pageable page) {
        return new ResponseEntity<>(commandeService.findAll(page), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommandeDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<>(commandeService.findById(id), HttpStatus.OK);
    }

    @GetMapping("client/{id}")
    public ResponseEntity<Page<CommandeDTO>> findCommandeByClientId(@PathVariable Long id, Pageable page) {
        return new ResponseEntity<>(commandeService.getCommandesByClientId(id , page), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<CommandeDTO> save(@RequestBody CommandeDTO commande) {
        return new ResponseEntity<>(commandeService.save(commande), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> delete(@PathVariable Long id) {
        commandeService.deleteCommandeById(id);
        return new ResponseEntity<>("Done: Commande deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommandeDTO> update(@RequestBody CommandeDTO commande, @PathVariable Long id) {
        return ResponseEntity.accepted().body(commandeService.update(commande, id));
    }
}
