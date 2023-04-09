package com.example.demoA.promotion;


import com.example.demoA.categorie.CategorieService;
import com.example.demoA.produit.ProduitService;
import com.example.demoA.promotion.PromotionService;
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

