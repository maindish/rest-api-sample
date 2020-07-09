package com.maindish.restapisample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication()
public class RestApiSampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestApiSampleApplication.class, args);
    }
}
