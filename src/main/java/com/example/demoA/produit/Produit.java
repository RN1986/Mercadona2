package com.example.demoA.produit;


import com.example.demoA.categorie.Categorie;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idproduit;
    private String libelle;
    private String description;
    private double prix;
    @ManyToOne
    @JoinColumn(name = "categorie_idcategorie")
    private Categorie categorie;

    public Produit() {
    }


}
