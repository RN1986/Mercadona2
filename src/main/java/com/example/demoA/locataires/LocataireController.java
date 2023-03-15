package com.example.demoA.locataires;

import com.example.demoA.appartements.Appartement;
import com.example.demoA.appartements.AppartementService;
import com.example.demoA.etatDesLieux.EtatDesLieux;
import com.example.demoA.etatDesLieux.EtatDesLieuxService;
import com.example.demoA.etatDesLieux.EtatDesLieuxType;
import com.example.demoA.paiement.Paiement;
import com.example.demoA.paiement.PaiementObjet;
import com.example.demoA.paiement.PaiementOrigine;
import com.example.demoA.paiement.PaiementService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class LocataireController {


    private final LocataireService locataireService;
    private final AppartementService appartementService;
    private final PaiementService paiementService;
    private final EtatDesLieuxService etatDesLieuxService;
    public LocataireController(LocataireService locataireService, AppartementService appartementService, PaiementService paiementService, EtatDesLieuxService etatDesLieuxService) {
        this.locataireService = locataireService;
        this.appartementService = appartementService;
        this.paiementService = paiementService;
        this.etatDesLieuxService = etatDesLieuxService;
    }

    //Créer Locataires
    @RequestMapping(path = "/createLocataire", method = RequestMethod.GET)
    public String getLocatairesCreated(Model model) {
        List<Locataire> locataires = locataireService.getLocataires();
        model.addAttribute("locataires", locataires);
        model.addAttribute("locataire_new", new Locataire());
        return "CreerLocataire";
    }

    @RequestMapping(path = "/createLocataire", method = RequestMethod.POST)
    public RedirectView createLocataire(RedirectAttributes redirectAttributes, @ModelAttribute Locataire locataire_new) {
        locataireService.creerLocataire(locataire_new);
        String message = "Locataire <b>" + locataire_new.getNom() + " " + locataire_new.getPrenom() + "</b>" + " ajouté ✨.";
        RedirectView redirectView = new RedirectView("/createLocataire", true);
        redirectAttributes.addFlashAttribute("locataireMessage", message);
        return redirectView;
    }

//Rechercher Locataires

    //OK
    @RequestMapping(path = "/searchLocataire", method = RequestMethod.GET)
    public String searchLocataire(@ModelAttribute("locataire_r") Locataire locataire_r, Model model) {

        return "ChercherLocataire";
    }

    //OK
    @RequestMapping(path = "/searchLocataire", method = RequestMethod.POST)
    public String getLocatairesSearched(@ModelAttribute("locataire_r") Locataire locataire_r, Model model) {

        model.addAttribute("locataire_r", new Locataire());

        List<Locataire> locataires_r = locataireService.getLocataireByNom(locataire_r.getNom());
        model.addAttribute("locataires_r", locataires_r);
        if (locataires_r.isEmpty()) {
            model.addAttribute("locataireMessage", "Aucun locataire enregistré sous ce nom");
        }

        return "ChercherLocataire";
    }

    //Accueil Locataires
    //ok
    @RequestMapping(path = "/AccueilLocataires", method = RequestMethod.GET)
    public String AccueilLocataire(Model model) {
        return "AccueilLocataires";
    }

    //Fiche Locataire
    //ok
    @RequestMapping(path = "/ficheLocataire/{locataire_id}", method = RequestMethod.GET)
    public String ficheLocataire(Model model, @PathVariable("locataire_id") Integer locataire_id) {
        Optional<Locataire> locataires = locataireService.getLocataireById(locataire_id);
        model.addAttribute("locataires", locataires);
        List<Appartement> appartements = appartementService.getAppartementsLibres();
        model.addAttribute("appartements", appartements);

        return "ficheLocataire";
    }
/*
    //ok
    @RequestMapping(path = "/ficheLocataire/{locataire_id}", method = RequestMethod.POST)
    public RedirectView updateLocataire(Model model, @PathVariable("locataire_id") Integer locataire_id, @RequestParam("nom") String nom, @RequestParam("prenom") String prenom, @RequestParam("email") String email,@RequestParam("appartement") Appartement appartement) {
        RedirectView redirectView = new RedirectView("/ficheLocataire/{locataire_id}", true);
        model.addAttribute("appartement", appartement);
        Optional<Locataire> locataires = locataireService.getLocataireById(locataire_id);
        if (locataires.isPresent()) {
            Locataire updatedLocataire = locataires.get();
            updatedLocataire.setNom(nom);
            updatedLocataire.setPrenom(prenom);
            updatedLocataire.setEmail(email);

            //updatedLocataire.setAppartement(appartement);
            locataireService.updateLocataire(updatedLocataire);
        }
        return redirectView;
    }
*/
    //OK
    @RequestMapping(path = "/ficheLocataire/{locataire_id}", method = RequestMethod.POST)
    public RedirectView updateLocataire(Model model, @PathVariable("locataire_id") Integer locataire_id,
                                        @RequestParam("nom") String nom, @RequestParam("prenom") String prenom,
                                        @RequestParam("email") String email) {

        RedirectView redirectView = new RedirectView("/ficheLocataire/{locataire_id}", true);

        Optional<Locataire> locataires = locataireService.getLocataireById(locataire_id);
        if (locataires.isPresent()) {
            Locataire updatedLocataire = locataires.get();
            updatedLocataire.setNom(nom);
            updatedLocataire.setPrenom(prenom);
            updatedLocataire.setEmail(email);

          /*  List<Appartement> appartements = appartementService.getAppartements();
            model.addAttribute("appartements", appartements);

            List <Appartement>  newlistAppart = updatedLocataire.getAppartements();

            newlistAppart.add(appartementService.findselonId(appartement.getAppartement_id()));
            updatedLocataire.setAppartements(newlistAppart);
*/
            locataireService.updateLocataire(updatedLocataire);
            model.addAttribute("locataireMessage", "Les modifications ont été enregistrées avec succès.");
        } else {
            model.addAttribute("locataireMessage", "Impossible de mettre à jour le locataire. Veuillez réessayer.");
        }

        return redirectView;
    }

    @RequestMapping(path = "/ficheLocataire/{locataire_id}/addappartement", method = RequestMethod.GET)
    public String addAppartement(Model model, @RequestParam("appartement") Integer appartement_id) {
        // récupération de l'appartement
        Appartement appartement = appartementService.findselonId(appartement_id);
        model.addAttribute("appartement", appartement);
        // création d'un état des lieux initialisé avec les valeurs du formulaire
        EtatDesLieux etatdeslieux = new EtatDesLieux(EtatDesLieuxType.ENTRÉE, "", LocalDate.now());
        Paiement paiement = new Paiement(PaiementOrigine.LOCATAIRE,appartementService.findselonId(appartement_id).getDepotdegarantie(),PaiementObjet.DEPOT_DE_GARANTIE,LocalDate.now(),appartementService.findselonId(appartement_id));
        model.addAttribute("etatdeslieux", etatdeslieux);
        // ajout d'un objet Paiement vide au modèle
        model.addAttribute("paiement", paiement);
        return "ficheLocataire";
    }

    @RequestMapping(path = "/ficheLocataire/{locataire_id}/addappartement", method = RequestMethod.POST)
    public RedirectView addAppartement(Model model, @PathVariable("locataire_id") Integer locataire_id,
                                       @ModelAttribute("paiement") Paiement paiement,
                                       @RequestParam("appartement") Integer appartement_id,
                                       @ModelAttribute("dateEDL")  @DateTimeFormat(pattern = "yyyy-MM-dd") String dateEDLStr,
                                       @ModelAttribute("dateDDG")  @DateTimeFormat(pattern = "yyyy-MM-dd") String dateDDGStr,
                                       @ModelAttribute("commentaires") String commentaires,
                                       @ModelAttribute("etatdeslieux") EtatDesLieux etatdeslieux) {
        LocalDate dateEDL = LocalDate.parse(dateEDLStr);
        LocalDate dateDDG = LocalDate.parse(dateDDGStr);

        RedirectView redirectView = new RedirectView("/ficheLocataire/{locataire_id}", true);

        model.addAttribute("appartement", appartementService.findselonId(appartement_id));


        Optional<Locataire> locataire = locataireService.getLocataireById(locataire_id);
        if (locataire.isPresent()) {
            Locataire updatedLocataire = locataire.get();

            List<Appartement> appartements = appartementService.getAppartementsLibres();
            model.addAttribute("appartements", appartements);

            List<Appartement> newlistAppart = updatedLocataire.getAppartements();
            newlistAppart.add(appartementService.findselonId(appartement_id));
            updatedLocataire.setAppartements(newlistAppart);


            paiement.setDate(dateDDG);
            paiement.setAppartement(appartementService.findselonId(appartement_id));
            paiement.setObjet(PaiementObjet.DEPOT_DE_GARANTIE);
            paiement.setMontant(appartementService.getDepotdegarantieById(appartement_id));
            paiement.setOrigine(PaiementOrigine.LOCATAIRE);
            paiementService.creerPaiement(paiement);
            List<Paiement> newlistPaiement = updatedLocataire.getPaiement();
            newlistPaiement.add(paiement);
            updatedLocataire.setPaiement(newlistPaiement);


            etatdeslieux.setType(EtatDesLieuxType.ENTRÉE);
            etatdeslieux.setCommentaires(commentaires);
            etatdeslieux.setDate(dateEDL);
            etatdeslieux.setLocataire(locataireService.getLocataireSelonId(locataire_id));


            etatDesLieuxService.creerEtatDesLieux(etatdeslieux);

            List<EtatDesLieux> newlistEDL = updatedLocataire.getEtatdeslieux();
            newlistEDL.add(etatdeslieux);
            updatedLocataire.setEtatdeslieux(newlistEDL);

            locataireService.updateLocataire(updatedLocataire);
            model.addAttribute("locataireMessage", "Les modifications ont été enregistrées avec succès.");
        } else {
            model.addAttribute("locataireMessage", "Impossible d'ajouter l'appartement. Veuillez réessayer.");
        }

        return redirectView;
    }

    //ok
    @RequestMapping(path = "/ficheLocataire/{locataire_id}/addpaiement", method = RequestMethod.GET)
    public String addPaiement(Model model, @PathVariable("locataire_id") Integer locataire_id) {
        model.addAttribute("appartementP", appartementService.getAppartementByLocataire(locataireService.getLocataireSelonId(locataire_id)));

        model.addAttribute("paiement_new", new Paiement());

        return "ficheLocataire";
    }

    @RequestMapping(path = "/ficheLocataire/{locataire_id}/addpaiement", method = RequestMethod.POST)
    public RedirectView addPaiement(Model model, @PathVariable("locataire_id") Integer locataire_id,
                                       @ModelAttribute("paiement_new") Paiement paiement,
                                       @ModelAttribute("date") String dateStr, @ModelAttribute("origine") String origine,
                                    @ModelAttribute("objet") String objet,@ModelAttribute("montant") Integer montant
                                       ) {
        LocalDate date = LocalDate.parse(dateStr);
        RedirectView redirectView = new RedirectView("/ficheLocataire/{locataire_id}", true);

       // model.addAttribute("appartementP", appartementService.getAppartementByLocataire(locataireService.getLocataireSelonId(locataire_id)));

        Optional<Locataire> locataire = locataireService.getLocataireById(locataire_id);
        if (locataire.isPresent()) {
            Locataire updatedLocataire = locataire.get();

            paiement.setOrigine(PaiementOrigine.valueOf(origine));
            paiement.setMontant(montant);
            paiement.setObjet(PaiementObjet.valueOf(objet));
            paiement.setDate(date);
            paiementService.creerPaiement(paiement);
            List<Paiement> newlistPaiement = updatedLocataire.getPaiement();
            newlistPaiement.add(paiement);
            updatedLocataire.setPaiement(newlistPaiement);

            locataireService.updateLocataire(updatedLocataire);
            model.addAttribute("locataireMessage", "Les modifications ont été enregistrées avec succès.");
        } else {
            model.addAttribute("locataireMessage", "Impossible d'ajouter l'appartement. Veuillez réessayer.");
        }

        return redirectView;
    }
}
/*
    //Supprimer un locataire

    //ok
    @RequestMapping(path = "/ficheLocataire/delete/{locataire_id}", method = RequestMethod.POST)
    public String deleteLocataire(RedirectAttributes redirectAttributes, @PathVariable("locataire_id") Integer locataire_id) {
        Optional<Locataire> optionalLocataire = locataireService.getLocataireById(locataire_id);
        if (optionalLocataire.isPresent()) {
            Locataire locataire = optionalLocataire.get();
            locataireService.deleteById(locataire_id);
            String message = "Locataire <b>" + locataire.getNom() + " " + locataire.getPrenom() + "</b>" + " supprimé ✨.";
            redirectAttributes.addFlashAttribute("locataireMessage", message);
        } else {
            redirectAttributes.addFlashAttribute("locataireMessage", "Le locataire n'existe pas.");
        }
        return "AccueilLocataires";
    }
    /*
    //Affecter un appartement au locataire
    //Rechercher Appartement

    //OK
    @RequestMapping(path = "/ficheLocataire/addAppartement/{locataire_id}", method = RequestMethod.GET)
    public String searchAppartement(@ModelAttribute("appartement_r") Appartement appartement_r, Model model) {

        return "ficheLocataire";
    }

    //OK
    @RequestMapping(path = "/ficheLocataire/addAppartement/{locataire_id}", method = RequestMethod.POST)
    public String getAppartementsSearched(@ModelAttribute("appartement_r") Appartement appartement_r, Model model) {

        model.addAttribute("appartement_r", new Appartement());
        List<Appartement> appartements_r = appartementService.getAppartementByCodepostal(appartement_r.getCodepostal());
        model.addAttribute("appartements_r", appartements_r);
        if (appartements_r.isEmpty()) {
            model.addAttribute("appartementMessage", "Aucun appartement enregistré sous ce nom");
        }

        return "ficheLocataire";
    }
    */


