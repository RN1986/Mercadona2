package com.example.demoA.paiement;

import com.example.demoA.appartements.Appartement;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Paiement {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer paiement_id;
        private String origine;
        private Integer montant;
        private String objet;
        private Date date;
        @ManyToOne
        @JoinColumn(name = "appartement_appartement_id")
        private Appartement appartement;


    }