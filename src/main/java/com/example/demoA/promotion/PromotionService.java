package com.example.demoA.promotion;

import com.example.demoA.categorie.CategorieRepository;
import com.example.demoA.categorie.CategorieService;
import com.example.demoA.promotion.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PromotionService {
    private final PromotionRepository promotionRepository;

    @Autowired
    public PromotionService(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }
}


