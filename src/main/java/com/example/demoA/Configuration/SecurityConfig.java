package com.example.demoA.Configuration;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailsService customUserDetailsService;

    @Autowired
    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/js/filtreCategorie.js", "/image/produit/**", "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html", "/css/style.css").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin() // Authentification via le formulaire de connexion
                .loginPage("/administrationauthentification") // Spécification de la page du formulaire de connexion
                .permitAll()
                .defaultSuccessUrl("/administration") // Redirige vers l'accueil de l'Administration après l'authentification réussie
                .and()
                .logout().permitAll()
                .logoutUrl("/administrationlogout") // Spécifie l'URL de déconnexion
                .and()
                .httpBasic()
                .and()
                .csrf().disable().cors();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public String BCryptPasswordEncoder(String mdp) {
        return BCryptPasswordEncoder(mdp);
    }


}



