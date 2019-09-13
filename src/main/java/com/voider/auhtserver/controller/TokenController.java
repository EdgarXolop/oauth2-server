package com.voider.auhtserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@EnableAuthorizationServer
@EnableResourceServer
@RestController
public class TokenController {

    @Autowired
    private JwtTokenStore tokenStore;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/token/revoke")
    public void revokeToken(Authentication authentication) {

        Optional.ofNullable(authentication).ifPresent(auth -> {
            OAuth2AccessToken accessToken = tokenStore.getAccessToken((OAuth2Authentication) auth);

            Optional.ofNullable(accessToken).ifPresent(oAuth2AccessToken -> {
                Optional.ofNullable(oAuth2AccessToken.getRefreshToken()).ifPresent(tokenStore::removeRefreshToken);
                tokenStore.removeAccessToken(accessToken);
            });
        });
    }
}