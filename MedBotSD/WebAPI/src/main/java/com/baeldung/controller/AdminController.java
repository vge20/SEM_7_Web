package com.baeldung.controller;

import com.baeldung.User.IUserService;
import com.baeldung.User.User;
import com.baeldung.authentication.Authentication;
import com.baeldung.config.AppConfig;
import com.baeldung.dto.AdminDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin
@RestController
public class AdminController {

    private IUserService userService;

    public AdminController() {
        try {
            AppConfig appConfig = new AppConfig();
            userService = appConfig.getUserServiceImpl();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/api/v1/admins/{id}")
    protected ResponseEntity<Object> doPut(@PathVariable int id, @RequestBody AdminDTO adminDTO) {
        if (Authentication.getPrivilegeLevel() < 1) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        User user = userService.getUserByLogin(adminDTO.getLogin());
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        user = new User(id, adminDTO.getLogin(), adminDTO.getPassword(), 1, adminDTO.getFirstName(),
                adminDTO.getLastName(), adminDTO.getGender(), adminDTO.getBirthDate());
        if (userService.updateUser(user)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

}
