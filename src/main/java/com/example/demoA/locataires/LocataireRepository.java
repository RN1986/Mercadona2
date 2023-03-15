package com.example.demoA.locataires;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.Access;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository
public interface LocataireRepository extends JpaRepository<Locataire,Integer> {
/*
    @Query("SELECT l FROM public.locataires l WHERE l.locataire_id=?1")
    Optional<Locataire> findLocataireById(Integer locataire_id);

    @Query("SELECT l FROM locataires l WHERE l.nom=?1 or l.prénom=?2")
    Optional<Locataire> findByNameContainingOrPrénomContaining(String nom, String prénom);
*/
   // @Query ("select l from locataires l")
    List<Locataire> findAll();

    Optional<Locataire> findById(Integer locataire_id);
    @Query("SELECT l FROM Locataire l WHERE l.locataire_id=?1")
    Locataire findByLocataire_id(Integer locataire_id);

    ArrayList<Locataire> findByNom(String nom);

}
