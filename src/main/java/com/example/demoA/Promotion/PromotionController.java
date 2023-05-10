package com.example.demoA.Promotion;


import com.example.demoA.Categorie.CategorieService;
import com.example.demoA.Produit.ProduitService;
import org.springframework.stereotype.Controller;


@Controller
public class PromotionController {
    private final ProduitService produitService;
    private final PromotionService promotionService;
    private final CategorieService categorieService;

    public PromotionController(ProduitService produitService, PromotionService promotionService, CategorieService categorieService) {
        this.produitService = produitService;
        this.promotionService = promotionService;
        this.categorieService = categorieService;
    }
}

