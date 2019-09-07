package com.voider.auhtserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.voider.auhtserver.model.user"} )
@EnableJpaRepositories("com.voider.auhtserver.dao.user")
public class AuhtserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuhtserverApplication.class, args);
    }

}
