package com.example.demoA.Configuration;
import com.example.demoA.Administrateurs.Administrateurs;
import com.example.demoA.Administrateurs.AdministrateursService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AuthentificationController {
    private static final Logger logger = LoggerFactory.getLogger(AuthentificationController.class);

    private AdministrateursService administrateursService;

    private CustomUserDetailsService customUserDetailsService;

    public AuthentificationController(CustomUserDetailsService customUserDetailsService,AdministrateursService administrateursService) {
        this.customUserDetailsService = customUserDetailsService;
        this.administrateursService = administrateursService;
    }

    @GetMapping("/administrationauthentification")
    public String AuthentificationAdministration(Model model) {
        model.addAttribute("erreurAuthentification", "Le nom d'utilisateur ou le mot de passe est incorrect. Veuillez essayer à nouveau");
        model.addAttribute("logout","Vous avez été déconnecté avec succès");
        return "authentification";
    }


}

