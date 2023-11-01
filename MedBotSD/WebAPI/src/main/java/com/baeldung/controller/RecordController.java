package com.baeldung.controller;

import com.baeldung.Record.IRecordService;
import com.baeldung.Record.Record;
import com.baeldung.config.AppConfig;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

@CrossOrigin
@RestController
public class RecordController {

    private IRecordService recordService;

    public RecordController() {
        try {
            AppConfig appConfig = new AppConfig();
            recordService = appConfig.getRecordServiceImpl();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/records")
    protected ResponseEntity<Object> doGet(@RequestParam String patientLogin, @RequestParam Date startDate,
                                           @RequestParam Date endDate, @RequestParam int limit,
                                           @RequestParam int skipped) {
        System.out.println("AAA");
        ArrayList<Record> recordsList = recordService.getRecordsByPatientDateInterval(patientLogin,
                                        startDate, endDate, limit, skipped);
        if (recordsList == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else {
            return new ResponseEntity<>(recordsList, HttpStatus.OK);
        }
    }
}
