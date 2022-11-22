package com.molla;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MollaShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(MollaShopApplication.class, args);
    }

}
