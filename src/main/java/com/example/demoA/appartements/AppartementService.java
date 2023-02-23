package com.example.demoA.appartements;

import org.springframework.stereotype.Service;

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
    public List<Appartement> getAppartementByCodepostal(Integer codepostal) {
        return appartementRepository.findByCodepostal(codepostal);
    }
    public Appartement creerAppartement(Appartement appartementNew) {

        return appartementRepository.save(appartementNew);
    }

    public Optional<Appartement> getAppartementById(Integer appartementId) {
        return appartementRepository.findAppartementById(appartementId);
    }

    public void updateAppartement(Appartement updatedAppartement) {appartementRepository.save(updatedAppartement);
    }

    public void deleteById(Integer appartementId) {
    }

    public Appartement findselonId(Integer appartementId) {
        return appartementRepository.findselonId(appartementId);
    }
    /*
public Optional <Appartement> getAppartementByLocataire_id(Integer Locataire_id) {
    return appartementRepository.findByLocataire_id(Locataire_id);}

     */

}
