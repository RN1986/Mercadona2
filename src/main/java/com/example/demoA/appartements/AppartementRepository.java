package com.example.demoA.appartements;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AppartementRepository extends JpaRepository <Appartement,Integer> {
    @Query("SELECT s FROM Appartement s WHERE s.appartement_id=?1")
    Optional<Appartement> findAppartementById(Integer appartement_id);
/*
    @Query("SELECT s FROM Appartement s WHERE s.adresse=?1 or s.code_postal=?2")
    List<Appartement> findByNameContainingOrEmailContaining(String adresse, Integer code_postal);
*/
    List<Appartement> findByCodepostal(Integer codepostal);
/*
    Optional<Appartement> findByLocataire_id(Integer locataire_id);

 */
    @Query ("SELECT s FROM Appartement s WHERE s.appartement_id=?1")
    Appartement findselonId (Integer appartement_id);
}
