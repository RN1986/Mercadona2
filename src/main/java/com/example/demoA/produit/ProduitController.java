package com.example.demoA.produit;


import com.example.demoA.categorie.CategorieService;
import com.example.demoA.promotion.PromotionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;


@Controller
public class ProduitController {
    private final ProduitService produitService;
    private final PromotionService promotionService;
    private final CategorieService categorieService;

    public ProduitController(ProduitService produitService, PromotionService promotionService, CategorieService categorieService) {
        this.produitService = produitService;
        this.promotionService = promotionService;
        this.categorieService = categorieService;
    }

    //Accueil Catalogue
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String getLocatairesCreated(Model model) {
        List<Produit> produits = produitService.findAll();
        model.addAttribute("produits", produits);
        //model.addAttribute("locataire_new", new Locataire());
        return "index";
    }
/*
    @RequestMapping(path = "/", method = RequestMethod.POST)
    public RedirectView createLocataire(RedirectAttributes redirectAttributes, @ModelAttribute Locataire locataire_new) {
        locataireService.creerLocataire(locataire_new);
        String message = "Locataire <b>" + locataire_new.getNom() + " " + locataire_new.getPrenom() + "</b>" + " ajouté ✨.";
        RedirectView redirectView = new RedirectView("/createLocataire", true);
        redirectAttributes.addFlashAttribute("locataireMessage", message);
        return redirectView;
    }
    */

}

