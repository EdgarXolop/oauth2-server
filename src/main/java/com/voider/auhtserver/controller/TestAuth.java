package com.voider.auhtserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import java.security.Principal;

@EnableAuthorizationServer
@EnableResourceServer
@RestController
public class TestAuth {

    @GetMapping("/me")
    public Principal user(Principal principal) {
        return principal;
    }
}
