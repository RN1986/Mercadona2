package com.example.demoA.Administrateurs;

import com.example.demoA.Produit.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministrateursRepository extends JpaRepository<Administrateurs, Long> {

    String findMotdepasseByNomutilisateur(String nomUtilisateur);

    Administrateurs findByNomutilisateur(String nomUtilisateur);
}
