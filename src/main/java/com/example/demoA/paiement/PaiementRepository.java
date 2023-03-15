package com.example.demoA.paiement;

import com.example.demoA.appartements.Appartement;
import com.example.demoA.locataires.Locataire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PaiementRepository extends JpaRepository<Paiement,Integer> {
    @Query("SELECT s FROM Paiement s WHERE s.paiement_id=?1")
    Paiement findSelonId (Integer paiement_id);




}
