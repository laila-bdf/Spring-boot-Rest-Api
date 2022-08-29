package com.valueit.springbootapp.model;
import java.sql.Date;
import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Commande {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date_commande;
    private Float prix_total;
    private String mode_payement;
    private String status;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Client client;


}
