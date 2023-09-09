package com.example.demoA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//vérifie si l'utilisateur est authentifié avant d'autoriser l'accès aux URL d'administration
/*
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
/*
    @Autowired
    private AuthenticationInterceptor authenticationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor)
                .addPathPatterns("/administration/**")
                .excludePathPatterns("/administrationauthentification");
    }

}
*/