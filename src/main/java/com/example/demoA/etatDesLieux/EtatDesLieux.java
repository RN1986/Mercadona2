package com.example.demoA.etatDesLieux;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class EtatDesLieux {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer etatdeslieux_id;
    private String type;
    private String commentaires;
    private Date date;

    public EtatDesLieux() {
    }

    public Integer getEtatdeslieux_id() {
        return etatdeslieux_id;
    }

    public void setEtatdeslieux_id(Integer etatdeslieux_id) {
        this.etatdeslieux_id = etatdeslieux_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(String commentaires) {
        this.commentaires = commentaires;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
