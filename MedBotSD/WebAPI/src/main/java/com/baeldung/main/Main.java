package com.baeldung.main;

import com.baeldung.controller.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = DoctorController.class)
@ComponentScan(basePackageClasses = PatientController.class)
@ComponentScan(basePackageClasses = AdminController.class)
@ComponentScan(basePackageClasses = RecordController.class)
@ComponentScan(basePackageClasses = AuthenticationController.class)
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
