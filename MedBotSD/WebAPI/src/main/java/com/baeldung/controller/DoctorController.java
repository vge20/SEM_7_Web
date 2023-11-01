package com.baeldung.controller;

import com.baeldung.Doctor.IDoctorService;
import com.baeldung.Doctor.Doctor;
import com.baeldung.config.AppConfig;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin
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

    @GetMapping("/api/v1/doctors")
    protected ResponseEntity<Object> doGet(@RequestParam String specialization, @RequestParam int limit,
                                           @RequestParam int skipped) {
        List<Doctor> doctorsList = doctorService.getDoctorsList(specialization, limit, skipped);
        if (doctorsList == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else {
            return new ResponseEntity<>(doctorsList, HttpStatus.OK);
        }
    }

    @GetMapping("/api/v1/doctors/{id}")
    protected ResponseEntity<Object> doGet(@PathVariable int id) {
        Doctor doctor = doctorService.getDoctorById(id);
        if (doctor == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else {
            return new ResponseEntity<>(doctor, HttpStatus.OK);
        }
    }

    @PostMapping("/api/v1/doctors")
    protected ResponseEntity<Object> doPost(@RequestBody Doctor doctor) {
        if (doctorService.addDoctor(doctor)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/api/v1/doctors/{id}")
    protected ResponseEntity<Object> doPut(@RequestBody Doctor doctor, @PathVariable int id) {
        doctor.setId(id);
        if (doctorService.updateDoctor(doctor)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/api/v1/doctors/{id}")
    protected ResponseEntity<Object> doDelete(@PathVariable int id) {
        if (doctorService.deleteDoctor(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
