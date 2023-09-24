package com.example.demoA.Categorie;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CategorieTest {
    @Test
    public void testConstructeurCategorie() {
        // Créez une instance de Categorie en utilisant le constructeur
        Long idcategorie = 1L;
        String libelle = "TestCategory";
        Date datecreation = new Date();
        Categorie categorie = new Categorie(idcategorie, libelle,datecreation);

        // Vérifiez si les valeurs des attributs correspondent à celles fournies lors de la création
        assertEquals(idcategorie, categorie.getIdcategorie());
        assertEquals(libelle, categorie.getLibelle());
    }

}