package com.example.demoA.etatDesLieux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtatDesLieuxService {
    private final EtatDesLieuxRepository etatDesLieuxRepository;
 @Autowired
    public EtatDesLieuxService(EtatDesLieuxRepository etatDesLieuxRepository) {
        this.etatDesLieuxRepository = etatDesLieuxRepository;

    }

    public void creerEtatDesLieux(EtatDesLieux etatDesLieux) {
     etatDesLieuxRepository.save(etatDesLieux);
    }

    public List <EtatDesLieux> findByAppartementAndLocataire(Integer appartement_id, Integer locataire_id) {
        return etatDesLieuxRepository.findSelonAppartementAndLocataire(appartement_id,locataire_id);
    }

    public EtatDesLieux findSelonId(Integer etatdeslieux_id) {
     return etatDesLieuxRepository.findSelonId(etatdeslieux_id);
    }

    public void updateEDL(EtatDesLieux updatedEDL) {
     etatDesLieuxRepository.save(updatedEDL);
    }

    public void deleteDEL(EtatDesLieux deletedDEL) {
     etatDesLieuxRepository.delete(deletedDEL);
    }
}
