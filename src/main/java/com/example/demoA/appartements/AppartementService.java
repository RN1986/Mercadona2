package com.example.demoA.appartements;

import com.example.demoA.locataires.Locataire;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@Service
public class AppartementService {
    private final AppartementRepository appartementRepository;

    public AppartementService(AppartementRepository appartementRepository) {
        this.appartementRepository = appartementRepository;
    }

    public List<Appartement> getAppartements() {
        return appartementRepository.findAll();
    }
    public List<Appartement> getAppartementsLibres() {
        return appartementRepository.getAppartementsLibres();
    }
    public List<Appartement> getAppartementByCodepostal(Integer codepostal) {
        return appartementRepository.findByCodepostal(codepostal);
    }
    public Appartement creerAppartement(Appartement appartementNew) {

        return appartementRepository.save(appartementNew);
    }

    public Optional<Appartement> getAppartementById(Integer appartementId) {
        return appartementRepository.findAppartementById(appartementId);
    }

    public Optional<Appartement> getAppartementByLocataire(Locataire locataire) {
        return appartementRepository.findAppartementSelonLocataire(locataire);
    }

    public void updateAppartement(Appartement updatedAppartement) {appartementRepository.save(updatedAppartement);
    }

    public void deleteById(Integer appartementId) {
    }

    public Appartement findselonId(Integer appartementId) {
        return appartementRepository.findselonId(appartementId);
    }

public Integer findLocataireIdByAppartementId (Integer appartement_id)
{
    return  appartementRepository.findLocataireIdByAppartementId (appartement_id);
};

    public Integer getDepotdegarantieById(Integer appartement_id) {
        return  appartementRepository.findDepotdegarantieById(appartement_id);
    }

public Integer getChargesByAppartement (Appartement appartement)
{
    return  appartementRepository.findChargesSelonAppartement(appartement);
}
}
