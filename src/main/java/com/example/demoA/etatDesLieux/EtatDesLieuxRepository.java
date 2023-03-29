package com.example.demoA.etatDesLieux;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EtatDesLieuxRepository extends JpaRepository<EtatDesLieux,Integer> {
    @Query("SELECT e FROM EtatDesLieux e WHERE e.appartement.appartement_id = ?1 AND e.locataire.locataire_id = ?2")
    List<EtatDesLieux> findSelonAppartementAndLocataire(Integer appartement_id, Integer locataire_id);
    @Query("SELECT e FROM EtatDesLieux e WHERE e.etatdeslieux_id=?1")
    EtatDesLieux findSelonId (Integer etatdeslieux_id);
    @Query("SELECT e.date FROM EtatDesLieux e WHERE e.locataire.locataire_id=?1 AND e.appartement.appartement_id = ?2 AND e.type=?3")
    LocalDate getDateByLocataireIdAndType(Integer locataire_id, Integer appartement_id, EtatDesLieuxType entrée);
}
