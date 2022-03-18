package com.gabrielsantos.creditcardvalidationapi;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class CreditCardValidationApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CreditCardValidationApiApplication.class, args);
    }

}
