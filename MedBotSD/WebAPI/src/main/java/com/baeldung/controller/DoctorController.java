package com.baeldung.controller;

import com.baeldung.Doctor.IDoctorService;
import com.baeldung.Doctor.Doctor;
import com.baeldung.config.AppConfig;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class DoctorController {

    private IDoctorService doctorService;

    public DoctorController() {
        try {
            AppConfig appConfig = new AppConfig();
            doctorService = appConfig.getDoctorServiceImpl();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private HttpHeaders generateHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Access-Control-Allow-Origin", "*");
        httpHeaders.add("Access-Control-Allow-Methods", "POST, PUT, PATCH, GET, DELETE");
        httpHeaders.add("Access-Control-Allow-Headers", "*");
        return httpHeaders;
    }

    @GetMapping("/api/v1/doctors")
    protected ResponseEntity<Object> doGet(@RequestParam String specialization, @RequestParam int limit,
                                           @RequestParam int skipped) {
        List<Doctor> doctorsList = doctorService.getDoctorsList(specialization, limit, skipped);
        return new ResponseEntity<>(doctorsList, generateHttpHeaders(), HttpStatus.OK);
    }
}
