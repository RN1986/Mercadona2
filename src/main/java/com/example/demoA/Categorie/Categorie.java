package com.example.demoA.Categorie;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Data
@Entity

public class Categorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idcategorie;
    private String libelle;

    public Categorie(Long idcategorie, String libelle) {
        this.idcategorie = idcategorie;
        this.libelle = libelle;
    }

    public Categorie() {
    }

    public Long getIdcategorie() {
        return idcategorie;
    }

    public void setIdcategorie(Long idcategorie) {
        this.idcategorie = idcategorie;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
