package com.example.demoA.etatDesLieux;

import com.example.demoA.appartements.Appartement;
import com.example.demoA.appartements.AppartementService;
import com.example.demoA.locataires.Locataire;
import com.example.demoA.locataires.LocataireService;
import com.example.demoA.paiement.Paiement;
import com.example.demoA.paiement.PaiementObjet;
import com.example.demoA.paiement.PaiementOrigine;
import com.example.demoA.paiement.PaiementService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;
import java.util.List;

@Controller
public class EtatDesLieuxController {

    private final LocataireService locataireService;
    private final AppartementService appartementService;
    private final PaiementService paiementService;
    private final EtatDesLieuxService etatDesLieuxService;

    public EtatDesLieuxController(LocataireService locataireService, AppartementService appartementService, PaiementService paiementService, EtatDesLieuxService etatDesLieuxService) {
        this.locataireService = locataireService;
        this.appartementService = appartementService;
        this.paiementService = paiementService;
        this.etatDesLieuxService = etatDesLieuxService;
    }

    @RequestMapping(path = "/ficheLocataire/{locataire_id}/appartement/{appartement_id}/etatsdeslieux", method = RequestMethod.GET)
    public String Etatsdeslieux(Model model, @PathVariable("locataire_id") Integer locataire_id, @PathVariable("appartement_id") Integer appartement_id,
                           @ModelAttribute("appartement") Appartement appartement, @ModelAttribute("etatdeslieux") EtatDesLieux etatdeslieux) {

        List <EtatDesLieux> etatsdeslieux = etatDesLieuxService.findByAppartementAndLocataire(appartement_id, locataire_id);
        model.addAttribute("etatsdeslieux", etatsdeslieux);
        model.addAttribute("etatdeslieux", etatdeslieux);
        appartement = appartementService.findselonId(appartement_id);
        model.addAttribute("appartement", appartement);
        Locataire locataire = locataireService.getLocataireSelonId(locataire_id);
        model.addAttribute("locataire", locataire);
        return "AfficherEtatDesLieux";
    }


    @RequestMapping(path = "/ficheLocataire/{locataire_id}/appartement/{appartement_id}/etatdeslieux/{etatdeslieux_id}", method = RequestMethod.GET)
    public String ficheEtatDesLieux(Model model, @PathVariable("locataire_id") Integer locataire_id, @PathVariable("etatdeslieux_id") Integer etatdeslieux_id,
                                @PathVariable("appartement_id") Integer appartement_id) {

        model.addAttribute("locataire", locataireService.getLocataireSelonId(locataire_id));
        model.addAttribute("appartement", appartementService.findselonId(appartement_id));
        model.addAttribute("etatdeslieux", etatDesLieuxService.findSelonId(etatdeslieux_id));

        return "ficheEtatDesLieux";
    }


    @RequestMapping(path = "/ficheLocataire/{locataire_id}/appartement/{appartement_id}/etatdeslieux/{etatdeslieux_id}", method = RequestMethod.POST)
    public RedirectView màjEtatDesLieux(Model model, @PathVariable("appartement_id") Integer appartement_id,
                                        @PathVariable("etatdeslieux_id") Integer etatdeslieux_id,
                                        @RequestParam("commentaires") String commentaires, @RequestParam("date") String dateStr, RedirectAttributes redirectAttributes){

        RedirectView redirectView = new RedirectView("/ficheLocataire/{locataire_id}/appartement/{appartement_id}/etatdeslieux/{etatdeslieux_id}", true);
        LocalDate date = LocalDate.parse(dateStr);
        EtatDesLieux updatedEDL= etatDesLieuxService.findSelonId(etatdeslieux_id);

        updatedEDL.setCommentaires(commentaires);
      //  updatedEDL.setType(EtatDesLieuxType.valueOf(type));
        updatedEDL.setDate(date);

        etatDesLieuxService.updateEDL(updatedEDL);
        redirectAttributes.addFlashAttribute("EDLMessage", "Les modifications ont été enregistrées avec succès.");

        return redirectView;
    }

    @RequestMapping(path = "/ficheLocataire/{locataire_id}/appartement/{appartement_id}/etatdeslieux/{etatdeslieux_id}/delete", method = RequestMethod.POST)
    public RedirectView supprimerEtatDesLieux(Model model, @PathVariable("etatdeslieux_id") Integer etatdeslieux_id) {
        RedirectView redirectView = new RedirectView("/ficheLocataire/{locataire_id}/appartement/{appartement_id}/etatsdeslieux", true);
        EtatDesLieux deletedDEL = etatDesLieuxService.findSelonId(etatdeslieux_id);
        etatDesLieuxService.deleteDEL(deletedDEL);

        return redirectView;
    }


}


