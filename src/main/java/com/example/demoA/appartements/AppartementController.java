package com.example.demoA.appartements;

import com.example.demoA.locataires.Locataire;
import com.example.demoA.locataires.LocataireService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Controller
public class AppartementController {
    private final AppartementService appartementService;
    private final LocataireService locataireService;
    public AppartementController(AppartementService appartementService, LocataireService locataireService) {
        this.appartementService = appartementService;
        this.locataireService = locataireService;
    }
    //Créer Appartements
    @RequestMapping(path = "/createAppartement", method = RequestMethod.GET)
    public String getAppartementsCreated(Model model) {
        List<Appartement> appartements = appartementService.getAppartements();
        model.addAttribute("appartements", appartements);
        model.addAttribute("appartement_new", new Appartement());
        return "CreerAppartement";
    }

    @RequestMapping(path = "/createAppartement", method = RequestMethod.POST)
    public RedirectView createAppartement(RedirectAttributes redirectAttributes, @ModelAttribute Appartement appartement_new) {
        appartementService.creerAppartement(appartement_new);
        String message = "L'appartement situé <b>" + appartement_new.getAdresse() + " </b> a été ajouté ✨.";
        RedirectView redirectView = new RedirectView("/createAppartement", true);
        redirectAttributes.addFlashAttribute("appartementMessage", message);
        return redirectView;
    }
    //Rechercher Appartement

    //OK
    @RequestMapping(path = "/searchAppartement", method = RequestMethod.GET)
    public String searchAppartement(@ModelAttribute("appartement_r") Appartement appartement_r, Model model) {

        return "ChercherAppartement";
    }

    //OK
    @RequestMapping(path = "/searchAppartement", method = RequestMethod.POST)
    public String getAppartementsSearched(@ModelAttribute("appartement_r") Appartement appartement_r, Model model) {

        model.addAttribute("appartement_r", new Appartement());

        List<Appartement> appartements_r = appartementService.getAppartementByCodepostal(appartement_r.getCodepostal());
        model.addAttribute("appartements_r", appartements_r);
        if (appartements_r.isEmpty()) {
            model.addAttribute("appartementMessage", "Aucun appartement enregistré sous ce nom");
        }

        return "ChercherAppartement";
    }
/*
    //Accueil Appartement
    @RequestMapping(path = "/AccueilLocataires", method = RequestMethod.GET)
    public String AccueilLocataire(Model model) {
        return "AccueilLocataires";
    }
*/
    //Fiche Appartement
    @RequestMapping(path = "/ficheAppartement/{appartement_id}", method = RequestMethod.GET)
    public String ficheAppartement(Model model, @PathVariable("appartement_id") Integer appartement_id) {
        Optional<Appartement> appartements = appartementService.getAppartementById(appartement_id);
        model.addAttribute("appartements", appartements);
        return "ficheAppartement";
    }

    @RequestMapping(path = "/ficheAppartement/{appartement_id}", method = RequestMethod.POST)
    public RedirectView updateAppartement(RedirectAttributes redirectAttributes, Model model, @PathVariable("appartement_id") Integer appartement_id, @RequestParam("adresse") String adresse, @RequestParam("complement") String complement, @RequestParam("codepostal") Integer codepostal, @RequestParam("loyer") Integer loyer, @RequestParam("charges") Integer charges, @RequestParam("depotdegarantie") Integer depotdegarantie) {
        RedirectView redirectView = new RedirectView("/ficheAppartement/{appartement_id}", true);
        ;
        Optional<Appartement> appartements = appartementService.getAppartementById(appartement_id);
        if (appartements.isPresent()) {
            Appartement updatedAppartement = appartements.get();
            updatedAppartement.setAdresse(adresse);
            updatedAppartement.setComplement(complement);
            updatedAppartement.setCodepostal(codepostal);
            updatedAppartement.setLoyer(loyer);
            updatedAppartement.setCharges(charges);
            updatedAppartement.setDepotdegarantie(depotdegarantie);
            appartementService.updateAppartement(updatedAppartement);
        }
        String message = "Votre modification a bien été prise en compte ✨.";

        redirectAttributes.addFlashAttribute("AppartementMessage", message);
        return redirectView;
    }
    //Supprimer un locataire

    //ok
    @RequestMapping(path = "/ficheAppartement/delete/{appartement_id}", method = RequestMethod.POST)
    public String deleteAppartement(RedirectAttributes redirectAttributes, @PathVariable("appartement_id") Integer appartement_id) {
        Optional<Appartement> optionalAppartement = appartementService.getAppartementById(appartement_id);
        if (optionalAppartement.isPresent()) {
            Appartement appartement = optionalAppartement.get();
            appartementService.deleteById(appartement_id);
            String message = "l'appartement situé <b>" + appartement.getAdresse() + "</b> supprimé ✨.";
            redirectAttributes.addFlashAttribute("appartementMessage", message);
        } else {
            redirectAttributes.addFlashAttribute("appartementMessage", "L'appartement n'existe pas.");
        }
        return "AccueilLocataires";
    }

    //Affecter un locataire
    @RequestMapping(path = "/affecterLocataire/{appartement_id}/{locataire_id}", method = RequestMethod.POST)
    public RedirectView affecterLocataire(RedirectAttributes redirectAttributes, @PathVariable("appartement_id") Integer appartement_id, @PathVariable("locataire_id") Integer locataire_id) {
        Optional<Appartement> optionalAppartement = appartementService.getAppartementById(appartement_id);
        Optional<Locataire> optionalLocataire = locataireService.getLocataireById(locataire_id);
        if (optionalAppartement.isPresent() && optionalLocataire.isPresent()) {
            Appartement appartement = optionalAppartement.get();
            Locataire locataire = optionalLocataire.get();
            appartement.setLocataire(locataire);
            appartementService.updateAppartement(appartement);
            String message = "Le locataire " + locataire.getNom() + " " + locataire.getPrenom() + " a été affecté à l'appartement situé " + appartement.getAdresse() + " ✨.";
            redirectAttributes.addFlashAttribute("appartementMessage", message);
        } else {
            redirectAttributes.addFlashAttribute("appartementMessage", "Impossible d'affecter le locataire à l'appartement. Veuillez vérifier les ID.");
        }
        RedirectView redirectView = new RedirectView("/ficheAppartement/" + appartement_id, true);
        return redirectView;
    }

}
