package com.baeldung.controller;

import com.baeldung.User.IUserService;
import com.baeldung.User.User;
import com.baeldung.authentication.Authentication;
import com.baeldung.config.AppConfig;
import com.baeldung.dto.AuthenticationDTO;
import com.baeldung.dto.UserRoleDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@CrossOrigin
@RestController
public class AuthenticationController {

    private IUserService userService;

    public AuthenticationController() {
        try {
            AppConfig appConfig = new AppConfig();
            userService = appConfig.getUserServiceImpl();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/api/v1/authentications")
    protected ResponseEntity<Object> doPost(@RequestBody AuthenticationDTO authenticationDTO) {
        User user = userService.logIn(authenticationDTO.getLogin(), authenticationDTO.getPassword());
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            Authentication.setPrivilegeLevel(user.getPrivilegeLevel());
            boolean role = false;
            if (user.getPrivilegeLevel() == 1) {
                role = true;
            }
            UserRoleDTO userRoleDTO = new UserRoleDTO(role);
            return new ResponseEntity<>(userRoleDTO, HttpStatus.OK);
        }
    }
}
