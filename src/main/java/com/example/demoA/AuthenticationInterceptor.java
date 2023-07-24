package com.example.demoA;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
//vérifie si l'utilisateur est authentifié avant de laisser passer la requête vers le contrôleur approprié
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();

        // Vérifie si l'utilisateur est authentifié
        if (session.getAttribute("authenticated") == null) {
            // Redirige l'utilisateur vers la page d'authentification
            response.sendRedirect(request.getContextPath() + "/administrationauthentification");
            return false;
        }

        // L'utilisateur est authentifié, permet l'accès à la ressource
        return true;
    }

}
