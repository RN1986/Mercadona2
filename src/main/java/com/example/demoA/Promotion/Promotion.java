package com.example.demoA.Promotion;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idpromotion;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate datedebut ;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate datefin;
    @Column(name = "remise", precision = 2)
    private int remise;


    public Promotion(LocalDate datedebut, LocalDate datefin, int remise) {
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.remise = remise;

        }

    public Promotion() {

    }

    public Long getIdpromotion() {
        return idpromotion;
    }

    public void setIdpromotion(Long idpromotion) {
        this.idpromotion = idpromotion;
    }

    public LocalDate getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(LocalDate datedebut) {
        this.datedebut = datedebut;
    }

    public LocalDate getDatefin() {
        return datefin;
    }

    public void setDatefin(LocalDate datefin) {
        this.datefin = datefin;
    }

    public double getRemise() {
        return remise;
    }

    public void setRemise(int remise) {
        this.remise = remise;
    }
}
