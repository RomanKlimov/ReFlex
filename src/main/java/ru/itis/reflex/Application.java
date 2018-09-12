package ru.itis.reflex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;



@SpringBootApplication
//@EntityScan(basePackages = {"ru.tver.hack.models", "ru.tver.hack.form", "ru.tver.hack.entities"})
//@EnableJpaRepositories(basePackages = "ru.tver.hack.repositories")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
