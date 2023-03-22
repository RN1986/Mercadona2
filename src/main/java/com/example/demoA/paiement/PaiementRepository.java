package com.example.demoA.paiement;

import com.example.demoA.appartements.Appartement;
import com.example.demoA.locataires.Locataire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaiementRepository extends JpaRepository<Paiement,Integer> {
    @Query("SELECT s FROM Paiement s WHERE s.paiement_id=?1")
    Paiement findSelonId (Integer paiement_id);

    @Query("SELECT p FROM Paiement p WHERE p.appartement.appartement_id = ?1 AND p.appartement.locataire.locataire_id = ?2")
    List<Paiement> findByAppartementAndLocataire(Integer appartement_id, Integer locataire_id);

}

