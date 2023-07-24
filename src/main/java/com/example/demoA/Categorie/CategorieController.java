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

    //Adminsitration : Affiche la page de création d'une catégorie
    @RequestMapping(path = "/administration/categorie", method = RequestMethod.GET)
    public String creerCategorie(Model model) {
        List<Categorie> categories = categorieService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("categorie_new", new Categorie());
        return "creerCategorie";
    }

    //Adminsitration : Crée la catégorie
    @RequestMapping(path = "/administration/categorie", method = RequestMethod.POST)
    public RedirectView creerCategorie(RedirectAttributes redirectAttributes, @ModelAttribute Categorie categorie_new,@RequestParam String libelle) {
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


}
