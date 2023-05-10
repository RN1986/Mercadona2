package com.example.demoA.Categorie;


import com.example.demoA.Produit.ProduitService;
import com.example.demoA.Promotion.PromotionService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;


@Controller
public class CategorieController {
    private final ProduitService produitService;
    private final PromotionService promotionService;
    private final CategorieService categorieService;

    public CategorieController(ProduitService produitService, PromotionService promotionService, CategorieService categorieService) {
        this.produitService = produitService;
        this.promotionService = promotionService;
        this.categorieService = categorieService;
    }

    //Créer catégorie
    @RequestMapping(path = "/administration/categorie", method = RequestMethod.GET)
    public String creerCategorie(Model model) {
        List<Categorie> categories = categorieService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("categorie_new", new Categorie());
        return "creerCategorie";
    }

    @RequestMapping(path = "/administration/categorie", method = RequestMethod.POST)
    public RedirectView createPaiement(RedirectAttributes redirectAttributes, @ModelAttribute Categorie categorie_new,@RequestParam String libelle) {
        try {
            categorie_new.setLibelle(libelle.toUpperCase());
            categorieService.creerCategorie(categorie_new);

            String message = "La catégorie <b>" + categorie_new.getLibelle() + " </b> a été ajoutée ✅";
            RedirectView redirectView = new RedirectView("/administration/categorie", true);
            redirectAttributes.addFlashAttribute("categorieCreee", message);
            return redirectView;
        } catch (DataIntegrityViolationException e) {
            String errorMessage = "La catégorie <b>" + libelle.toUpperCase() + " </b> est déjà enregistrée ❌";
            redirectAttributes.addFlashAttribute("erreurCategorie", errorMessage);
            return new RedirectView("/administration/categorie", true);
        }
    }

/*
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
