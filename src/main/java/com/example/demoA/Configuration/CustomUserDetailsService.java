package com.example.demoA.Configuration;

import com.example.demoA.Administrateurs.Administrateurs;
import com.example.demoA.Administrateurs.AdministrateursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

        Administrateurs administrateur = administrateursRepository.findByNomutilisateur(user);

        if (administrateur == null) {
            throw new UsernameNotFoundException("Utilisateur : " + user+" non trouvé.");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();

        return new User(administrateur.getNomutilisateur(), administrateur.getMotdepasse(), authorities);
    }


}

