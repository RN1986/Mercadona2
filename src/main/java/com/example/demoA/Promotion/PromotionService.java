package com.example.demoA.Promotion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.lang.Math.round;

@Service
public class PromotionService {
    private final PromotionRepository promotionRepository;


    public PromotionService(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }


}


