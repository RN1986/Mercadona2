package com.example.demoA.Categorie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieService {
    private final CategorieRepository categorieRepository;

    @Autowired
    public CategorieService(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    public List<Categorie> findAll() {
        return categorieRepository.findAll();
    }

    public void creerCategorie(Categorie categorieNew) { categorieRepository.save(categorieNew);
    }

    public Categorie getSelonLibelle(String libelle){return categorieRepository.getSelonLibelle (libelle);};

    public Categorie getById(Long idcategorie) {return categorieRepository.getById (idcategorie);
    }


}


