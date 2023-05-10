package com.example.demoA.Administrateurs;

import com.example.demoA.Produit.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministrateursService {
    private final AdministrateursRepository administrateursRepository;
@Autowired
    public AdministrateursService(AdministrateursRepository administrateursRepository) {
        this.administrateursRepository = administrateursRepository;
    }
    public List<Administrateurs> findAll() {
        return administrateursRepository.findAll();
    }
}
