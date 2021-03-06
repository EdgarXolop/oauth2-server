package com.voider.auhtserver.config;

import com.voider.auhtserver.model.user.User;

public class CustomUser extends org.springframework.security.core.userdetails.User {

    private static final long serialVersionUID = 1L;

    public CustomUser(User user) {
        super(user.getEmail(), user.getPassword(), user.getGrantedAuthoritiesList());
    }
}