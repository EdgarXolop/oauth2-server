package com.voider.auhtserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {
    private String clientid = "acme";
    private String clientSecret = "acmesecret";
    private String privateKey = "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEpQIBAAKCAQEAp6eQXkYi8s5k0Z6pUdhcFzVuXAlAhYQyuk5CWg5n23mPM3iS\n" +
            "CBRQqyrOZbUALp2VYqSLV8QEkjZDE4mLJaYfVQK8S1vvNUXGYsPihuwya3oSRoyr\n" +
            "aU+qfAujnRPwMdsKnViV51kOEvQfah32Obzzd9NoQ2Uhzn+3T3MDmvlH5l64h9H2\n" +
            "e5goUOnzxswdoEPZDP/CaWp/4/SZMlRcCNlPN2Q+AoeTXpC5DkWCCfqo1dvT9hr9\n" +
            "TDDlGTT5A8C8UH+2wGKMpN/R4Ps9h3e8pnaCIXEm9oPRHvXQD/dyzs544Kc8YW0q\n" +
            "2voJk3ZW8eMVJbEwSmfX4lmpOY2xjtxmwoP7DQIDAQABAoIBAGoTsVqU0eQDBvNC\n" +
            "iitI0+uzWOJO0E4qONZ8UasdVkUdgqe0b+FAyVGjwffEMBtOhYhmR+v+EE5amtIv\n" +
            "wqTNmSF2m6CqyI1SWpV/D0MDC1vJXurYclQtndbinmDyjpVc3Dh2JlK8nBUN9Rp1\n" +
            "XqdlkD6Ound584vubxOL2AokE4QEAEqm6UN1DbfWJjnR2c9PqdCV3Yq+RdAhIkGs\n" +
            "uvFiU1vDaKeKLyQCUD6/OEk6jFyQjIDh5yDnsteotdd3vCqXpl4enrBDqheVrg0k\n" +
            "h1BjT2CwwHeduMtqsJHSVlNbkpGykMHiVDLeTOWEr9ihcicCx2N4TXI3LZcG4NF8\n" +
            "fxuMAwECgYEA1HqJEn1qJNtzyprTABohj/e6SU+BQrEVMdTy9ixDJlmFMbVyCooQ\n" +
            "ExEUWr5UqOG7dFOejWgQBw7DXvkRuxOCCvQuX3tah35OvJFOLCe06ZWV5DiVTlMI\n" +
            "ZsjxosHdO4k2AfNfLnP+y1bNy1VUoqK8DqEkLmcQcYXVbPs7munIEe0CgYEAyf6l\n" +
            "QzOxExj62Kd1qp+lpKmnINzK0uWb1ge1KMcuOfel5sJLHkhZjgQcIblZu2ftftPy\n" +
            "kLF/qQ/RUoYsWKGvhJ7ew5X9sEG1H+F25M9xbNxpGes+5xmx5YO0dE+Ou2OVz5v0\n" +
            "r3uGRusX9eDH0jbEl1S8R12MqR/knvU24ETg6aECgYEAyKeXPrDVRk1AAjtHu7bx\n" +
            "rwWhFI5SYWrduxuA/l06ZMLxd8oEElPjbj4Y3MEVLg9OeUJmXt/BEwksE0pfJi38\n" +
            "BuAl0uGo0n/DV2xZSHWO8W/kB4wv47GiI37sQmmVxR2EtT7hyfqcyeYFnlnM/XF0\n" +
            "6tPeWTvXWX2r2ak/1sGjwNkCgYEAxSfeaHRozmogVR1GNS6z/79zmBjJgwWHkGVf\n" +
            "hhi7XusAK/bSWRFnD72lM6l1Ev1CivsWk/Ib14jP18uMN0pBRaHHboHU5peL0T1A\n" +
            "zd5IKGgN0bWSZQDoaUue87O/bdmqnMPtFObtTgUjEuO+RckdI1QanVGK/HxbqmdP\n" +
            "k6DHImECgYEAuTAaU00Z0F7x4fPewRga7zPtF239/oowXWOc9fYRE1fc4tejD36U\n" +
            "NJydGu7hdT8fhswB4b9l3tmoRQr1IvMnHn+ABKbyd267qwUbziOiBPHVnanDzFXT\n" +
            "SR0yXuHfx7qWDZV5rOpdzEuIetsKCyeFyDQNtHDuywJnZXRzCmiHhDE=\n" +
            "-----END RSA PRIVATE KEY-----\n";
    private String publicKey = "-----BEGIN PUBLIC KEY-----\n" +
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAp6eQXkYi8s5k0Z6pUdhc\n" +
            "FzVuXAlAhYQyuk5CWg5n23mPM3iSCBRQqyrOZbUALp2VYqSLV8QEkjZDE4mLJaYf\n" +
            "VQK8S1vvNUXGYsPihuwya3oSRoyraU+qfAujnRPwMdsKnViV51kOEvQfah32Obzz\n" +
            "d9NoQ2Uhzn+3T3MDmvlH5l64h9H2e5goUOnzxswdoEPZDP/CaWp/4/SZMlRcCNlP\n" +
            "N2Q+AoeTXpC5DkWCCfqo1dvT9hr9TDDlGTT5A8C8UH+2wGKMpN/R4Ps9h3e8pnaC\n" +
            "IXEm9oPRHvXQD/dyzs544Kc8YW0q2voJk3ZW8eMVJbEwSmfX4lmpOY2xjtxmwoP7\n" +
            "DQIDAQAB\n" +
            "-----END PUBLIC KEY-----\n";

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Bean
    public JwtAccessTokenConverter tokenEnhancer() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(privateKey);
        converter.setVerifierKey(publicKey);
        return converter;
    }
    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(tokenEnhancer());
    }
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
                .accessTokenConverter(tokenEnhancer());
    }
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient(clientid).secret(passwordEncoder.encode(clientSecret)).scopes("read", "write")
                .authorizedGrantTypes("password","authorization_code", "refresh_token").accessTokenValiditySeconds(20000)
                .refreshTokenValiditySeconds(20000);

    }
}