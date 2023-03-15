package com.example.demoA.paiement;

import com.example.demoA.appartements.Appartement;
import com.example.demoA.appartements.AppartementService;
import com.example.demoA.etatDesLieux.EtatDesLieuxService;
import com.example.demoA.locataires.Locataire;
import com.example.demoA.locataires.LocataireService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;


@Controller
public class PaiementController {
    private final LocataireService locataireService;
    private final AppartementService appartementService;
    private final PaiementService paiementService;
    private final EtatDesLieuxService etatDesLieuxService;

    public PaiementController(LocataireService locataireService, AppartementService appartementService, PaiementService paiementService, EtatDesLieuxService etatDesLieuxService) {
        this.locataireService = locataireService;
        this.appartementService = appartementService;
        this.paiementService = paiementService;
        this.etatDesLieuxService = etatDesLieuxService;
    }

    //Créer Paiements
    @RequestMapping(path = "/createPaiement", method = RequestMethod.GET)
    public String getPaiementsCreated(Model model) {
        List<Paiement> paiements = paiementService.getPaiements();
        model.addAttribute("paiements", paiements);
        model.addAttribute("paiement_new", new Paiement());
        List<Appartement> appartements = appartementService.getAppartements();
        model.addAttribute("appartements", appartements);
           return "CreerPaiement";
    }

    @RequestMapping(path = "/createPaiement", method = RequestMethod.POST)
    public RedirectView createPaiement(RedirectAttributes redirectAttributes,
                                       @ModelAttribute Paiement paiement_new,
                                       @ModelAttribute ("date") @DateTimeFormat(pattern = "yyyy-MM-dd") String dateStr) {
        System.out.println("dateStr=" + dateStr);

        try {
            LocalDate date = LocalDate.parse(dateStr);
            paiement_new.setDate(date);
            paiementService.creerPaiement(paiement_new);
            String message = "Le paiement de <b>" + paiement_new.getMontant() + " € </b> a été ajouté ✨.";
            RedirectView redirectView = new RedirectView("/createPaiement", true);
            redirectAttributes.addFlashAttribute("paiementMessage", message);
            return redirectView;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /*
    @RequestMapping(path = "/createPaiement", method = RequestMethod.POST)
    public RedirectView createPaiement(RedirectAttributes redirectAttributes,
                                       @ModelAttribute Paiement paiement_new,
                                       @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") String dateStr) {


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateStr,formatter);


        paiement_new.setDate(date);
        paiementService.creerPaiement(paiement_new);
        String message = "Le paiement de <b>" + paiement_new.getMontant() + " € </b> a été ajouté ✨.";
        RedirectView redirectView = new RedirectView("/createPaiement", true);
        redirectAttributes.addFlashAttribute("paiementMessage", message);
        return redirectView;
    }
/*
/*
    @RequestMapping(path = "/createPaiement", method = RequestMethod.POST)
    public RedirectView createPaiement(RedirectAttributes redirectAttributes, @ModelAttribute Paiement paiement_new,
                                       @ModelAttribute LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

        //String date = "16/08/2016";

        date = LocalDate.parse(date, formatter);
        paiementService.creerPaiement(paiement_new);
        String message = "Le paiement de <b>" + paiement_new.getMontant() + " € </b> a été ajouté ✨.";
        RedirectView redirectView = new RedirectView("/createPaiement", true);
        redirectAttributes.addFlashAttribute("paiementMessage", message);
        return redirectView;
    }
    */
}
