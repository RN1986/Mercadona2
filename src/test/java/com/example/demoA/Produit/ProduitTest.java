package com.example.demoA.Produit;

import com.example.demoA.Promotion.Promotion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class ProduitTest {

    private Produit produit;

    @BeforeEach
    public void setUp() {
        produit = new Produit();
    }

    @Test
    public void testUpdatePromotion_PromotionEnCoursDeValidite() {
        Promotion promotion = new Promotion();
        promotion.setDatedebut(LocalDate.now().minusDays(1)); // Promotion a commencé hier
        promotion.setDatefin(LocalDate.now().plusDays(1));    // Promotion se termine demain
        promotion.setRemise(10); // Remise de 10%
        produit.setPromotion(promotion);

        produit.updatePromotion(); // Appel de la méthode

        // La promotion est toujours valide, elle ne devrait pas être mise à jour.
        assertNotNull(produit.getPromotion());
    }

    @Test
    public void testUpdatePromotion_PromotionExpiree() {
        Promotion promotion = new Promotion();
        promotion.setDatedebut(LocalDate.now().minusDays(5)); // Promotion a commencé il y a 5 jours (déjà expirée)
        promotion.setDatefin(LocalDate.now().minusDays(1));  // Promotion expirée hier
        promotion.setRemise(20); // Remise de 20%
        produit.setPromotion(promotion);

        produit.updatePromotion(); // Appel de la méthode

        // La promotion est expirée, elle devrait être mise à jour (définie à null).
        assertNull(produit.getPromotion());
    }

    @Test
    public void testUpdatePromotion_SansPromotion() {
        // Le produit n'a pas de promotion.
        produit.updatePromotion(); // Appel de la méthode

        // Comme il n'y a pas de promotion, rien ne devrait changer.
        assertNull(produit.getPromotion());
    }

    @Test
    public void testUpdatePromotion_PromotionInvalide() {
        Promotion promotion = new Promotion();
        promotion.setDatedebut(LocalDate.now().plusDays(1)); // Promotion commence demain
        promotion.setDatefin(LocalDate.now().plusDays(5));   // Promotion se termine dans 5 jours
        promotion.setRemise(0); // Pas de remise
        produit.setPromotion(promotion);

        produit.updatePromotion(); // Appel de la méthode

        // La promotion est invalide (remise à 0%), elle devrait être mise à jour (définie à null).
        assertNull(produit.getPromotion());
    }
}
