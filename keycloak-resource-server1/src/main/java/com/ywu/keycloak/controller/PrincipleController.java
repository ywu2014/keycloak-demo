package com.ywu.keycloak.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class PrincipleController {

    @ResponseBody
    @GetMapping(path = "/protected/principle")
    public Object getPrinciple(Principal principal) {
        /*KeycloakSecurityContext keycloakContext = (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());

        AccessToken token = keycloakContext.getToken();
        System.out.println("realm:" + keycloakContext.getRealm() + ", token:" + keycloakContext.getTokenString());

        return token;*/

        return principal;
    }

    @GetMapping(path = "/logout")
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "/";
    }
}
