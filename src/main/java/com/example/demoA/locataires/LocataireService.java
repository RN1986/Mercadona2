package com.example.demoA.locataires;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocataireService {
    private final LocataireRepository locataireRepository;

    @Autowired
    public LocataireService(LocataireRepository locataireRepository) {
        this.locataireRepository = locataireRepository;
    }

   public List<Locataire> getLocataires() {
        return locataireRepository.findAll();
    }

    public Locataire creerLocataire(Locataire locataire) {
        return locataireRepository.save(locataire);
    }

    public Optional <Locataire> getLocataireById(Integer locataire_id) {
        return locataireRepository.findById(locataire_id);
    }
     public List<Locataire> getLocataireByNom(String nom) {
        return locataireRepository.findByNom(nom);
    }

    public void deleteById(Integer locataire_id) {
        locataireRepository.deleteById(locataire_id);
    }

    public void updateLocataire(Locataire updatedLocataire) {
        locataireRepository.save(updatedLocataire);
    }
}

