package com.baeldung.app;

import com.baeldung.controller.DoctorController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = DoctorController.class)
public class MedBotApp {
    public static void main(String[] args) {
        SpringApplication.run(MedBotApp.class, args);
    }

}