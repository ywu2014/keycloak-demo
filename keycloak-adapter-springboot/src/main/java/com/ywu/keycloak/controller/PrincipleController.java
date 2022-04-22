package com.ywu.keycloak.controller;

import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PrincipleController {

    @ResponseBody
    @GetMapping(path = "/protected/principle")
    public Object getPrinciple(Model model, HttpServletRequest request){
        KeycloakSecurityContext keycloakContext = (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());

        AccessToken token = keycloakContext.getToken();
        System.out.println("realm:" + keycloakContext.getRealm() + ", token:" + keycloakContext.getTokenString());

        return token;
    }

    @GetMapping(path = "/logout")
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "/";
    }
}
