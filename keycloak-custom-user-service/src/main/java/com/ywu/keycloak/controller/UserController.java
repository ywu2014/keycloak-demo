package com.ywu.keycloak.controller;

import com.ywu.keycloak.dto.UserDto;
import com.ywu.keycloak.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ywu
 * @date 2022/5/2 19:51
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("{id}")
    public UserDto get(@PathVariable String id) {
        return userService.getUserById(id);
    }
}
