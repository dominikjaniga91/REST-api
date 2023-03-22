package com.epam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author Dominik_Janiga
 */
@SpringBootApplication
//@EnableWebMvc
class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
