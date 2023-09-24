package com.example.demoA.Categorie;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity

public class Categorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idcategorie;
    @Column(name = "libelle", nullable = false, unique=true)
    private String libelle;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "datecreation", nullable = false)
    private Date datecreation;
    public Categorie(Long idcategorie, String libelle,Date datecreation) {
        this.idcategorie = idcategorie;
        this.libelle = libelle;
        this.datecreation = datecreation;
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
