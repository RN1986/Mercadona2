package com.example.demoA.Administrateurs;



import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "administrateurs")
public class Administrateurs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "nomutilisateur", nullable = false, unique=true)
    private String nomutilisateur;

    @Column(name = "motdepasse", nullable = false)
    private String motdepasse;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "datecreation", nullable = false)
    private Date datecreation;
    public Administrateurs() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomutilisateur() {
        return nomutilisateur;
    }

    public void setNomutilisateur(String nomutilisateur) {
        this.nomutilisateur = nomutilisateur;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }
}
