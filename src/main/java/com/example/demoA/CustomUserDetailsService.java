package com.example.demoA;

import com.example.demoA.Administrateurs.Administrateurs;
import com.example.demoA.Administrateurs.AdministrateursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    private final AdministrateursRepository administrateursRepository;

    @Autowired
    public CustomUserDetailsService(AdministrateursRepository administrateursRepository) {
        this.administrateursRepository = administrateursRepository;
    }
/*
    @PostMapping("/administrationauthentification")
    public String handleLogin(@RequestParam("user") String user,
                              @RequestParam("password") String password,
                              RedirectAttributes redirectAttributes) {

        System.out.println("USER dans controleur : "+ user);
        // Perform authentication using Spring Security
        UserDetails administrateur = loadUserByUsername(user);

        // Comparaison du mot de passe avec celui encodé enregistré dans les détails de l'utilisateur
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (administrateur != null && passwordEncoder.matches(password, administrateur.getPassword())) {
            // Authentication successful, redirect to the administration home page
            return "redirect:/AccueilAdministration";
        } else {
            // Authentication failed, show error message on the login page
            redirectAttributes.addFlashAttribute("erreurAuthentification", "Identifiants invalides");
            return "redirect:/authentification";
        }
    }*/
    @Override
    public  UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {
        //logger.info("User: {}", user);
       // user = "admin";
        System.out.println("USER dans loedUser : " + user);

        Administrateurs administrateur = administrateursRepository.findByNomutilisateur(user);

        if (administrateur == null) {
            throw new UsernameNotFoundException("User not found: " + user);
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        // Add user roles as authorities if you have them stored in your user entity


        return new User(administrateur.getNomutilisateur(), administrateur.getMotdepasse(), authorities);
    }


}

