package com.example.demoA.etatDesLieux;

import com.example.demoA.locataires.Locataire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtatDesLieuxRepository extends JpaRepository<EtatDesLieux,Integer> {
}
