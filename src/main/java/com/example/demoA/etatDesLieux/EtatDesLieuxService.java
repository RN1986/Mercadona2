package com.example.demoA.etatDesLieux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EtatDesLieuxService {
    private final EtatDesLieuxRepository etatDesLieuxRepository;
 @Autowired
    public EtatDesLieuxService(EtatDesLieuxRepository etatDesLieuxRepository, EtatDesLieuxRepository etatDesLieuxRepository1) {
        this.etatDesLieuxRepository = etatDesLieuxRepository1;

    }

    public void creerEtatDesLieux(EtatDesLieux etatDesLieux) {
     etatDesLieuxRepository.save(etatDesLieux);
    }
}
