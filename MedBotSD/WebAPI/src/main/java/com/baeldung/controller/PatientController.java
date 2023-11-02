package com.baeldung.controller;

import com.baeldung.User.IUserService;
import com.baeldung.User.User;
import com.baeldung.authentication.Authentication;
import com.baeldung.config.AppConfig;
import com.baeldung.dto.PatientDTO;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
public class PatientController {

    private IUserService userService;

    public PatientController() {
        try {
            AppConfig appConfig = new AppConfig();
            userService = appConfig.getUserServiceImpl();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/api/v1/patients")
    protected ResponseEntity<Object> doGet(@RequestParam boolean gender, @RequestParam String substr,
                                           @RequestParam int limit, @RequestParam int skipped) {
        if (Authentication.getPrivilegeLevel() < 1) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        List<User> usersList = userService.getUsersListBySubstrGender(gender, substr, limit, skipped);
        if (usersList == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else {
            return new ResponseEntity<>(usersList, HttpStatus.OK);
        }
    }

    @GetMapping("/api/v1/patients/{login}")
    protected ResponseEntity<Object> doGet(@PathVariable String login) {
        User user = userService.getUserByLogin(login);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

    @PostMapping("/api/v1/patients")
    protected ResponseEntity<Object> doPost(@RequestBody PatientDTO patientDTO) {
        if (Authentication.getPrivilegeLevel() < 0) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        User user = new User(patientDTO.getLogin(), patientDTO.getPassword(), 0,
                patientDTO.getFirstName(), patientDTO.getLastName(),
                patientDTO.getGender(), patientDTO.getBirthDate());
        if (userService.addUser(user)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/api/v1/patients/{login}")
    protected ResponseEntity<Object> doPut(@PathVariable String login, @RequestBody PatientDTO patientDTO) {
        if (Authentication.getPrivilegeLevel() < 0) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        User user = userService.getUserByLogin(login);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        user = new User(user.getId(), patientDTO.getLogin(), patientDTO.getPassword(), 0,
                patientDTO.getFirstName(), patientDTO.getLastName(),
                patientDTO.getGender(), patientDTO.getBirthDate());
        if (userService.updateUser(user)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/api/v1/patients/{login}")
    protected ResponseEntity<Object> doDelete(@PathVariable String login) {
        if (Authentication.getPrivilegeLevel() < 0) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        User user = userService.getUserByLogin(login);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (userService.deleteUser(user.getId())) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
