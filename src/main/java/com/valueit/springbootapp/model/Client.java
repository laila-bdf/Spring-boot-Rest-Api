package com.valueit.springbootapp.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@Entity
public class Client {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nom;
    private String prenom;
    private Date dateNaiss;
    @OneToMany(mappedBy = "client" , cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Commande> commandes = new ArrayList<Commande>();

}