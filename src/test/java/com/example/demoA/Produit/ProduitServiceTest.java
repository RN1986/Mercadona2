package com.example.demoA.Produit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import com.example.demoA.Categorie.CategorieRepository;
import com.example.demoA.Produit.Produit;
import com.example.demoA.Produit.ProduitRepository;
import com.example.demoA.Produit.ProduitService;
import com.example.demoA.Promotion.Promotion;
import com.example.demoA.Promotion.PromotionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProduitServiceTest {
    @Autowired
    private ProduitService produitService;

    /*
        @BeforeEach
        public void setUp() {
            produitService = new ProduitService(); // Créez une instance de ProduitService ou utilisez l'injection de dépendance.
        }
    */

    @Test
    public void testCalculerPrixEnCours_AvecPromotion() {
        Produit produit = new Produit();
        Promotion promotion = new Promotion();
        promotion.setDatedebut(LocalDate.now().minusDays(1)); // Promotion commençant hier
        promotion.setDatefin(LocalDate.now().plusDays(1));    // Promotion se terminant demain
        promotion.setRemise(10); // Remise de 10%
        produit.setPromotion(promotion);
        produit.setPrixDeBase(100.0); // Prix de base de 100 euros

        double prixEnCours = produitService.calculerPrixEnCours(produit);

        // Le prix en cours devrait être de 90 euros (remise de 10% appliquée)
        assertEquals(90.0, prixEnCours, 0.01);
    }

    @Test
    public void testCalculerPrixEnCours_SansPromotion() {
        Produit produit = new Produit();
        produit.setPrixDeBase(100.0); // Prix de base de 100 euros

        double prixEnCours = produitService.calculerPrixEnCours(produit);

        // En l'absence de promotion, le prix en cours doit être égal au prix de base.
        assertEquals(100.0, prixEnCours, 0.01);
    }
}
