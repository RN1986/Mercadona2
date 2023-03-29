package com.example.demoA.appartements;


import com.example.demoA.locataires.Locataire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AppartementRepository extends JpaRepository <Appartement,Integer> {
    @Query("SELECT s FROM Appartement s WHERE s.appartement_id=?1")
    Optional<Appartement> findAppartementById(Integer appartement_id);

    @Query("SELECT s FROM Appartement s WHERE s.locataire is null")
    List<Appartement> getAppartementsLibres();

    List<Appartement> findByCodepostal(Integer codepostal);

    @Query ("SELECT s FROM Appartement s WHERE s.appartement_id=?1")
    Appartement findselonId (Integer appartement_id);

        @Query("SELECT l.locataire_id FROM Locataire l JOIN l.appartements a WHERE a.appartement_id = :appartement_id")
        Integer findLocataireIdByAppartementId(@Param("appartement_id") Integer appartement_id);

   @Query ("SELECT s.depotdegarantie FROM Appartement s WHERE s.appartement_id=?1")
    Integer findDepotdegarantieById (Integer appartement_id);

    @Query ("SELECT s FROM Appartement s WHERE s.locataire=?1")
    List<Appartement> findAppartementSelonLocataire(Locataire locataire);


    @Query ("SELECT c.charges FROM Appartement c WHERE c.appartement_id=?1")
    Integer findChargesSelonAppartement(Appartement appartement);

}


