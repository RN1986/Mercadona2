package com.example.demoA.locataires;

import com.example.demoA.etatDesLieux.EtatDesLieuxRepository;
import com.example.demoA.etatDesLieux.EtatDesLieuxType;
import com.example.demoA.paiement.PaiementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class LocataireService {
    private final LocataireRepository locataireRepository;
    private final EtatDesLieuxRepository etatDesLieuxRepository;
    private final PaiementRepository paiementRepository;

    @Autowired
    public LocataireService(LocataireRepository locataireRepository,
                            EtatDesLieuxRepository etatDesLieuxRepository,
                            PaiementRepository paiementRepository) {
        this.locataireRepository = locataireRepository;
        this.etatDesLieuxRepository = etatDesLieuxRepository;
        this.paiementRepository = paiementRepository;
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

    public Locataire getLocataireSelonId(Integer locataire_id) {
        return locataireRepository.findByLocataire_id(locataire_id);
    }
    private  static  int getNombreDeMoisEntreDeuxDates(LocalDate dateDebut, LocalDate dateFin) {
        Period period = Period.between(dateDebut.withDayOfMonth(1), dateFin.withDayOfMonth(1));
        return period.getYears() * 12 + period.getMonths();
    }
    public  Integer SoldeLoyer (Integer locataire_id, Integer appartement_id) {
        LocalDate DateEntree = etatDesLieuxRepository.getDateByLocataireIdAndType(locataire_id,appartement_id, EtatDesLieuxType.ENTRÉE);
        System.out.println(DateEntree);
        Integer NbPaiementsLoyer = paiementRepository.nbpaiementsLoyer(locataire_id,appartement_id);
        Integer NbPaiementsCharges = paiementRepository.nbpaiementsCharges(locataire_id,appartement_id);
        return (NbPaiementsLoyer+NbPaiementsCharges)/2-getNombreDeMoisEntreDeuxDates(DateEntree,LocalDate.now());
    }

}

