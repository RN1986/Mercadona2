package com.example.demoA.Administrateurs;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Service
public class AdministrateursService  {
    private final AdministrateursRepository administrateursRepository;
@Autowired
    public AdministrateursService(AdministrateursRepository administrateursRepository) {
        this.administrateursRepository = administrateursRepository;
    }
    public List<Administrateurs> findAll() {
        return administrateursRepository.findAll();
    }

    // Méthode pour récupérer le mot de passe haché en fonction du nom d'utilisateur
    public String getMotDePasseHache(String nomUtilisateur) {
        return administrateursRepository.findMotdepasseByNomutilisateur(nomUtilisateur);
    }


}
