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
    /*    http.authorizeRequests()
                .antMatchers("/").permitAll() // Allow all to access the login page
                .anyRequest().authenticated()
                .and()
                .httpBasic();


        http.authorizeRequests()
                        .antMatchers("/","/image/display/**","/image/saveImageDetails").permitAll() // Permettre à tous d'accéder à la page d'authentification
                        .anyRequest().authenticated()
                        .and()
                .httpBasic();
*/
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
                .httpBasic();
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
/*
@Configuration
public class SecurityConfig  {

    @Bean
    public SecurityFilterChain apiSecurity (HttpSecurity http) throws Exception {

        http.authorizeHttpRequests((auth) -> auth
                .antMatchers("/").permitAll() // Permettre à tous d'accéder à la page d'authentification
                         .anyRequest()
                .authenticated()
        )
                .httpBasic();
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService (){
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("mdp"))
             .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Utiliser bcrypt comme algorithme de hachage des mots de passe
        return new BCryptPasswordEncoder();
    }

    /*
    @Autowired
    private YourUserDetailsService userDetailsService; // Remplacez YourUserDetailsService par votre propre service utilisateur
/*
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("passage par SecurityConfif ?");
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
*/
/*
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/administrationauthentification").permitAll() // Permettre à tous d'accéder à la page d'authentification
                //.anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/administrationauthentification") // Spécifier la page d'authentification personnalisée
                .defaultSuccessUrl("/administration") // Rediriger vers l'accueil après l'authentification réussie
                .and()
                .logout()
                .logoutUrl("/administrationlogout") // Spécifier l'URL de déconnexion personnalisée
                .logoutSuccessUrl("/administrationauthentification") // Rediriger après la déconnexion réussie
                .and()
                .csrf().disable(); // Désactiver la protection CSRF pour simplifier l'exemple
    }
/*
    @Bean
    public PasswordEncoder passwordEncoder() {
        // Utiliser bcrypt comme algorithme de hachage des mots de passe
        return new BCryptPasswordEncoder();
    }
/*
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
*/


