package com.baeldung.controller;

import com.baeldung.Record.IRecordRepository;
import com.baeldung.Record.IRecordService;
import com.baeldung.Record.Record;
import com.baeldung.User.IUserService;
import com.baeldung.User.User;
import com.baeldung.authentication.Authentication;
import com.baeldung.config.AppConfig;
import com.baeldung.dto.PostRecordDTO;
import com.baeldung.dto.RecordDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class RecordController {

    private IRecordService recordService;

    private IRecordRepository recordRepository;

    private IUserService userService;

    public RecordController() {
        try {
            AppConfig appConfig = new AppConfig();
            recordService = appConfig.getRecordServiceImpl();
            userService = appConfig.getUserServiceImpl();
            recordRepository  = appConfig.getRecordRepositoryImpl();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/api/v1/records/{id}")
    protected ResponseEntity<Object> doGet(@PathVariable int id) throws Exception {
        if (Authentication.getPrivilegeLevel() < 0) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        Record resRecord = recordRepository.getRecordById(id);
        if (resRecord == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(resRecord, HttpStatus.OK);
        }
    }

    @GetMapping("/api/v1/records")
    protected ResponseEntity<Object> doGet(@RequestParam String patientLogin, @RequestParam Date startDate,
                                           @RequestParam Date endDate, @RequestParam int limit,
                                           @RequestParam int skipped) {
        if (Authentication.getPrivilegeLevel() < 0) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        ArrayList<Record> recordsList = recordService.getRecordsByPatientDateInterval(patientLogin,
                                        startDate, endDate, limit, skipped);
        if (recordsList == null) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            List<RecordDTO> recordDTOList = new ArrayList<>();
            for (int i = 0; i < recordsList.size(); i++) {
                RecordDTO recordDTO = new RecordDTO();
                recordDTO.setId(recordsList.get(i).getId());
                recordDTO.setDoctorId(recordsList.get(i).getIdDoctor());
                recordDTO.setPatientLogin(patientLogin);
                recordDTO.setDate(recordsList.get(i).getDate());
                recordDTO.setStartTime(recordsList.get(i).getStartTime());
                recordDTO.setEndTime(recordsList.get(i).getEndTime());
                recordDTOList.add(recordDTO);
            }
            return new ResponseEntity<>(recordDTOList, HttpStatus.OK);
        }
    }

    @PostMapping("/api/v1/records")
    protected ResponseEntity<Object> doPost(@RequestBody PostRecordDTO recordDTO) {
        if (Authentication.getPrivilegeLevel() < 0) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        User user = userService.getUserByLogin(recordDTO.getPatientLogin());
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Record record = new Record(recordDTO.getId(), recordDTO.getDoctorId(), user.getId(), recordDTO.getDate(),
                recordDTO.getStartTime(), recordDTO.getEndTime());
        if (recordService.addRecord(record)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PatchMapping("/api/v1/records/{id}")
    protected ResponseEntity<Object> doPatch(@PathVariable int id, @RequestBody RecordDTO recordDTO) {
        if (Authentication.getPrivilegeLevel() < 0) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        User user = userService.getUserByLogin(recordDTO.getPatientLogin());
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Record record = new Record(id, recordDTO.getDoctorId(), user.getId(), recordDTO.getDate(),
                recordDTO.getStartTime(), recordDTO.getEndTime());
        if (recordService.updateRecord(record)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/api/v1/records")
    protected ResponseEntity<Object> doDelete(@RequestParam int doctorId, @RequestParam String patientLogin,
                                              @RequestParam Date date, @RequestParam Time startTime,
                                              @RequestParam Time endTime) {
        if (Authentication.getPrivilegeLevel() < 0) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        User user = userService.getUserByLogin(patientLogin);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (recordService.deleteRecordByParams(doctorId, user.getId(), date, startTime, endTime)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
