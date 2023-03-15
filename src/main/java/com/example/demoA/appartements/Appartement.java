package com.example.demoA.appartements;

import com.example.demoA.locataires.Locataire;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Appartement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer appartement_id;
    private String adresse;
    private String ville;
    private String complement;
    private Integer codepostal;
    private Integer loyer;
    private Integer charges;
    private Integer depotdegarantie;
    @ManyToOne
    @JoinColumn(name = "locataire_id")
    private Locataire locataire;

    public Appartement() {
    }

    public Appartement(Integer appartement_id, String adresse,String ville, String complement, Integer codepostal, Integer loyer, Integer charges, Integer depotdegarantie) {
        this.appartement_id = appartement_id;
        this.adresse = adresse;
        this.ville = ville;
        this.complement = complement;
        this.codepostal = codepostal;
        this.loyer = loyer;
        this.charges = charges;
        this.depotdegarantie = depotdegarantie;
    }

    public Integer getAppartement_id() {
        return appartement_id;
    }

    public void setAppartement_id(Integer appartement_id) {
        this.appartement_id = appartement_id;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public Integer getCodepostal() {
        return codepostal;
    }

    public void setCodepostal(Integer codepostal) {
        this.codepostal = codepostal;
    }

    public Integer getLoyer() {
        return loyer;
    }

    public void setLoyer(Integer loyer) {
        this.loyer = loyer;
    }

    public Integer getCharges() {
        return charges;
    }

    public void setCharges(Integer charges) {
        this.charges = charges;
    }

    public Integer getDepotdegarantie() {
        return depotdegarantie;
    }

    public void setDepot_de_garantie(Integer depot_de_garantie) {
        this.depotdegarantie = depotdegarantie;
    }

    public void setDepotdegarantie(Integer depotdegarantie) {
        this.depotdegarantie = depotdegarantie;
    }

    public Locataire getLocataire() {
        return locataire;
    }

    public void setLocataire(Locataire locataire) {
        this.locataire = locataire;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
}
