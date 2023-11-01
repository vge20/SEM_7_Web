package com.baeldung.controller;

import com.baeldung.Doctor.Doctor;
import com.baeldung.Doctor.IDoctorService;
import com.baeldung.Record.IRecordService;
import com.baeldung.Record.Record;
import com.baeldung.User.IUserService;
import com.baeldung.User.User;
import com.baeldung.config.AppConfig;
import com.baeldung.dto.RecordDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

@CrossOrigin
@RestController
public class RecordController {

    private IRecordService recordService;

    private IUserService userService;

    public RecordController() {
        try {
            AppConfig appConfig = new AppConfig();
            recordService = appConfig.getRecordServiceImpl();
            userService = appConfig.getUserServiceImpl();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/api/v1/records")
    protected ResponseEntity<Object> doGet(@RequestParam String patientLogin, @RequestParam Date startDate,
                                           @RequestParam Date endDate, @RequestParam int limit,
                                           @RequestParam int skipped) {
        ArrayList<Record> recordsList = recordService.getRecordsByPatientDateInterval(patientLogin,
                                        startDate, endDate, limit, skipped);
        if (recordsList == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else {
            return new ResponseEntity<>(recordsList, HttpStatus.OK);
        }
    }

    @PostMapping("/api/v1/records")
    protected ResponseEntity<Object> doPost(@RequestBody RecordDTO recordDTO) {
        User user = userService.getUserByLogin(recordDTO.getPatientLogin());
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Record record = new Record(recordDTO.getId(), recordDTO.getDoctorId(), user.getId(), recordDTO.getDate(),
                recordDTO.getStartTime(), recordDTO.getEndTime());
        if (recordService.addRecord(record)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
