package com.baeldung.controller;

import com.baeldung.Doctor.IDoctorService;
import com.baeldung.Doctor.Doctor;
import com.baeldung.authentication.Authentication;
import com.baeldung.config.AppConfig;
import com.baeldung.dto.DoctorDTO;
import com.baeldung.dto.PostDoctorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
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
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            List<DoctorDTO> doctorDTOList = new ArrayList<>();
            for (int i = 0; i < doctorsList.size(); i++) {
                DoctorDTO doctorDTO = new DoctorDTO();
                doctorDTO.setId(doctorsList.get(i).getId());
                doctorDTO.setFirstName(doctorsList.get(i).getFirstName());
                doctorDTO.setLastName(doctorsList.get(i).getLastName());
                doctorDTO.setGender(doctorsList.get(i).getGender());
                doctorDTO.setSpecialization(doctorsList.get(i).getSpecialization());
                doctorDTOList.add(doctorDTO);
            }
            return new ResponseEntity<>(doctorDTOList, HttpStatus.OK);
        }
    }

    @GetMapping("/api/v1/doctors/{id}")
    protected ResponseEntity<Object> doGet(@PathVariable int id) {
        if (Authentication.getPrivilegeLevel() < 1) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        Doctor doctor = doctorService.getDoctorById(id);
        if (doctor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            DoctorDTO doctorDTO = new DoctorDTO();
            doctorDTO.setId(doctor.getId());
            doctorDTO.setFirstName(doctor.getFirstName());
            doctorDTO.setLastName(doctor.getLastName());
            doctorDTO.setGender(doctor.getGender());
            doctorDTO.setSpecialization(doctor.getSpecialization());
            return new ResponseEntity<>(doctorDTO, HttpStatus.OK);
        }
    }

    @PostMapping("/api/v1/doctors")
    protected ResponseEntity<Object> doPost(@RequestBody PostDoctorDTO doctorDTO) {
        if (Authentication.getPrivilegeLevel() < 1) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        Doctor doctor = new Doctor(doctorDTO.getFirstName(), doctorDTO.getLastName(),
                doctorDTO.getGender(), doctorDTO.getSpecialization());
        if (doctorService.addDoctor(doctor)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/api/v1/doctors/{id}")
    protected ResponseEntity<Object> doPut(@RequestBody DoctorDTO doctorDTO, @PathVariable int id) {
        if (Authentication.getPrivilegeLevel() < 1) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        Doctor doctor = doctorService.getDoctorById(id);
        if (doctor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        doctor = new Doctor(doctorDTO.getFirstName(), doctorDTO.getLastName(),
                doctorDTO.getGender(), doctorDTO.getSpecialization());
        doctor.setId(id);
        if (doctorService.updateDoctor(doctor)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/api/v1/doctors/{id}")
    protected ResponseEntity<Object> doDelete(@PathVariable int id) {
        if (Authentication.getPrivilegeLevel() < 1) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        if (doctorService.deleteDoctor(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
