package com.valueit.springbootapp.dto;

import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@Data
public class ClientDTO {

    private long id;
    private String nom;
    private String prenom;
    private Date dateNaiss;
}