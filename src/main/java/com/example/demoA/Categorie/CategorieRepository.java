package com.example.demoA.Categorie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
    @Query("SELECT c FROM Categorie c WHERE c.libelle=?1")
    Categorie getSelonLibelle (String libelle);
}