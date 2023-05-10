package com.example.demoA.Produit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long>{
@Query("SELECT p FROM Produit p where p.id=?1")
    Produit getSelonId (Long i);
    @Query("SELECT p FROM Produit p where p.libelle=?1")
    List<Produit> getSelonLibelle(String libelle);
}

