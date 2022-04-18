package com.ywu.keycloak.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ResourceController {

    @ResponseBody
    @RequestMapping("/protected/resource")
    public String protectedResource() {
        return "this is protected resource";
    }

    @ResponseBody
    @RequestMapping("/common/resource")
    public String commonResource() {
        return "this is common resource";
    }

}
