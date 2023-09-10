package com.example.demoA;
import com.example.demoA.Administrateurs.Administrateurs;
import com.example.demoA.Administrateurs.AdministrateursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
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
    public String showLoginPage(Model model) {
        model.addAttribute("erreurAuthentification", "Le nom d'utilisateur ou le mot de passe est incorrect. Veuillez essayer à nouveau");
        model.addAttribute("logout","Vous avez été déconnecté avec succès");
        return "authentification"; // Return the login page
    }

    Map<String, String> passwords = new HashMap(); //Contient le mot de passe associé à chaque utilisateur

    public void passwordsInit() {
        List<Administrateurs> administrateurs = administrateursService.findAll();
        for (Administrateurs administrateur : administrateurs) {
            passwords.put(administrateur.getNomutilisateur(), administrateur.getMotdepasse());
        }
    }


    // Helper method to check if the password matches the hashed password
    private boolean passwordMatches(String hashedPassword, String plainPassword) {
        // Implement your secure password comparison logic here
        // For example, using BCryptPasswordEncoder
         BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
         return passwordEncoder.matches(plainPassword, hashedPassword);

        // In a real application, use a secure password hashing algorithm.
        // For demonstration purposes, we'll compare passwords as plain text (not recommended for production).
        //return hashedPassword.equals(plainPassword);
    }
}

