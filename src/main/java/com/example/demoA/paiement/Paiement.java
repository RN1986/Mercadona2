package com.example.demoA.paiement;

import com.example.demoA.appartements.Appartement;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@Entity
public class Paiement {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer paiement_id;
        @Enumerated(EnumType.STRING)
        private PaiementOrigine origine;
        private Integer montant;
        @Enumerated(EnumType.STRING)
        private PaiementObjet objet;
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private LocalDate date;
        @ManyToOne
        @JoinColumn(name = "appartement_appartement_id")
        private Appartement appartement;

        public Paiement() {
                this.paiement_id = null;
                this.origine = null;
                this.montant = null;
                this.objet = null;
                this.date = null;
                this.appartement = null;
        }

        public Paiement(PaiementOrigine origine, Integer montant, PaiementObjet objet, LocalDate date, Appartement appartement) {
                this.paiement_id = paiement_id;
                this.origine = origine;
                this.montant = montant;
                this.objet = objet;
                this.date = date;
                this.appartement = appartement;
        }

        public Integer getPaiement_id() {
                return paiement_id;
        }

        public void setPaiement_id(Integer paiement_id) {
                this.paiement_id = paiement_id;
        }

        public PaiementOrigine getOrigine() {
                return origine;
        }

        public void setOrigine(PaiementOrigine origine) {
                this.origine = origine;
        }

        public Integer getMontant() {
                return montant;
        }

        public void setMontant(Integer montant) {
                this.montant = montant;
        }

        public PaiementObjet getObjet() {
                return objet;
        }

        public void setObjet(PaiementObjet objet) {
                this.objet = objet;
        }

        public LocalDate getDate() {
                return date;
        }

        public void setDate(LocalDate date) {
                this.date = date;
        }

        public Appartement getAppartement() {
                return appartement;
        }

        public void setAppartement(Appartement appartement) {
                this.appartement = appartement;
        }
}