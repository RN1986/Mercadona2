package com.example.demoA.paiement;

import com.example.demoA.etatDesLieux.EtatDesLieux;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PaiementService {
    private final PaiementRepository paiementRepository;

    @Autowired
    public PaiementService(PaiementRepository paiementRepository) {
        this.paiementRepository = paiementRepository;
    }

    public List<Paiement> getPaiements() {
        return paiementRepository.findAll();
    }


    public Paiement findSelonId(Integer paiement_id) {
        return paiementRepository.findSelonId(paiement_id);
    }

    public Paiement creerPaiement(Paiement paiementNew) {
        return paiementRepository.save(paiementNew);
    }

    public List findByAppartementAndLocataire(Integer appartementId, Integer locataireId) {
        return paiementRepository.findByAppartementAndLocataire(appartementId, locataireId);
    }

    public Object getById(Integer paiement_id) {
        return  paiementRepository.findById(paiement_id);
    }

    public void updatePaiement(Paiement updatedpaiement) { paiementRepository.save(updatedpaiement);    }

    public void deletePaiement(Paiement deletedpaiement) {paiementRepository.delete(deletedpaiement);
    }

    public PaiementOrigine getOrigineSelonID(Integer paiement_id) {
        return paiementRepository.findOrigineSelonId(paiement_id) ;

    }

    public PaiementObjet getObjetSelonID(Integer paiement_id) {
        return paiementRepository.findObjetSelonId(paiement_id);
    }
}


