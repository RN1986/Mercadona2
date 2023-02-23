package com.example.demoA.locataires;
import com.example.demoA.appartements.Appartement;
import com.example.demoA.etatDesLieux.EtatDesLieux;
import com.example.demoA.paiement.Paiement;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Data
@Entity
public class Locataire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer locataire_id;
    private String nom;
    private String prenom;
    private String email;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "locataire_id")
    private List<Appartement> appartements;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "locataire_id")
    private List<EtatDesLieux> etatdeslieux;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "locataire_id")
    private List<Paiement> paiement;

    public Locataire(String nom, String prenom) {
        this.nom=nom;
        this.prenom=prenom;
        }

    public Locataire() {
    }

    public Integer getLocataire_id() {
        return locataire_id;
    }

    public void setLocataire_id(Integer locataire_id) {
        this.locataire_id = locataire_id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Appartement> getAppartements() {
        return appartements;
    }

    public void setAppartements(List<Appartement> appartements) {
        this.appartements = appartements;
    }

    public List<EtatDesLieux> getEtatdeslieux() {
        return etatdeslieux;
    }

    public void setEtatdeslieux(List<EtatDesLieux> etatdeslieux) {
        this.etatdeslieux = etatdeslieux;
    }

    public List<Paiement> getPaiement() {
        return paiement;
    }

    public void setPaiement(List<Paiement> paiement) {
        this.paiement = paiement;
    }
}
