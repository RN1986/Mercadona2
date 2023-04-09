package com.example.demoA.categorie;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
public class Categorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idproduit;
    private String libelle;

}
