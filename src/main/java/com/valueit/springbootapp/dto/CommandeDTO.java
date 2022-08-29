package com.valueit.springbootapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.valueit.springbootapp.model.Client;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@NoArgsConstructor
@Data
public class CommandeDTO {
    private Long id;
    private Date date_commande;
    private Float prix_total;
    private String mode_payement;
    private String status;
    private ClientDTO client;
}
