package com.example.demoA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

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
                .antMatchers("/", "/image/saveImageDetails", "/image/display/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin() // Utilisation de l'authentification basée sur formulaire
                .loginPage("/administrationauthentification") // Spécification de la page de connexion personnalisée
                .permitAll() // Autoriser tout le monde à accéder à la page de connexion
                .defaultSuccessUrl("/administration") // Rediriger vers l'accueil après l'authentification réussie
                .and()
                .logout().permitAll()
                .logoutUrl("/administrationlogout") // Spécifier l'URL de déconnexion personnalisée
                //.logoutSuccessUrl("/administrationauthentification") // Rediriger après la déconnexion réussie
                .and()
                .httpBasic()
               .and()
               .csrf().disable().cors();;

    }



    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}



