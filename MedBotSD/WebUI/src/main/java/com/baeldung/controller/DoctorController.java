package com.baeldung.controller;

import com.baeldung.Doctor.Doctor;
import com.baeldung.Doctor.IDoctorService;
import com.baeldung.config.AppConfig;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class DoctorController {

    private IDoctorService doctorService;

    public DoctorController() {
        AppConfig appConfig;
        try {
            appConfig = new AppConfig();
            doctorService = appConfig.getDoctorServiceImpl();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/doctors")
    protected ResponseEntity<Object> doGet(@RequestParam String specialization,
                                           @RequestParam int limit, @RequestParam int skipped) {
        List<Doctor> doctorsList = doctorService.getDoctorsList(specialization, limit, skipped);
        return new ResponseEntity<>(doctorsList, HttpStatus.OK);
    }

    @PostMapping("/doctors")
    protected ResponseEntity<Object> doPost(@RequestBody Doctor doctor) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/doctors/{id}")
    protected ResponseEntity<Object> doGet(@PathVariable int id) {
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PutMapping("/doctors/{id}")
    protected ResponseEntity<Object> doPut(@PathVariable int id, @RequestBody Doctor doctor) {
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
