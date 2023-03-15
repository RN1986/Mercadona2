package com.example.demoA.etatDesLieux;

import com.example.demoA.locataires.Locataire;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Data
@Entity
public class EtatDesLieux {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer etatdeslieux_id;
    @Enumerated(EnumType.STRING)
    private EtatDesLieuxType type;
    private String commentaires;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "locataire_id")
    private Locataire locataire;


    public EtatDesLieux() {
    }

    public EtatDesLieux(EtatDesLieuxType type, String commentaires, LocalDate date) {
        this.type = type;
        this.commentaires = commentaires;
        this.date = date;
    }

    public Integer getEtatdeslieux_id() {
        return etatdeslieux_id;
    }

    public void setEtatdeslieux_id(Integer etatdeslieux_id) {
        this.etatdeslieux_id = etatdeslieux_id;
    }

    public EtatDesLieuxType getType() {
        return type;
    }

    public void setType(EtatDesLieuxType type) {
        this.type = type;
    }

    public String getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(String commentaires) {
        this.commentaires = commentaires;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Locataire getLocataire() {
        return locataire;
    }

    public void setLocataire(Locataire locataire) {
        this.locataire = locataire;
    }
}
