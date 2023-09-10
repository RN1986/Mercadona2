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

