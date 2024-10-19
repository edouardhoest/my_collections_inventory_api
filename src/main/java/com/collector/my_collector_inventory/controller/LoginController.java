package com.collector.my_collector_inventory.controller;

import com.collector.my_collector_inventory.PasswordHasher;
import com.collector.my_collector_inventory.entities.UserInformation;
import com.collector.my_collector_inventory.exception.UserAlreadyFoundException;
import com.collector.my_collector_inventory.exception.UserDoesNotExistsException;
import com.collector.my_collector_inventory.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public ResponseEntity<Long> getUserInformations(@RequestBody UserInformation requestedUserInformation) throws Exception {
        UserInformation userInformation = loginService.findByUsername(requestedUserInformation.getUsername());
        if (userInformation == null) {
            throw new UserDoesNotExistsException(requestedUserInformation.getUsername());
        }
        if(PasswordHasher.verifyPassword(userInformation.getPassword(), requestedUserInformation.getPassword())) {
            return ResponseEntity.ok(userInformation.getId());
        }else{
            throw new Exception("Password is incorrect");
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Long> addUser(@RequestBody UserInformation requestedUserInformation) {
        UserInformation userInformation = loginService.findByUsername(requestedUserInformation.getUsername());
        if (userInformation != null) {
            throw new UserAlreadyFoundException(userInformation.getUsername());
        }
        return ResponseEntity.ok(loginService.registerUser(requestedUserInformation));
    }

    @PostMapping("/delete")
    public void deleteUser(@RequestBody UserInformation requestedUserInformation) throws Exception {
        UserInformation userInformation = loginService.findByUsername(requestedUserInformation.getUsername());
        if (userInformation == null) {
            throw new UserDoesNotExistsException(requestedUserInformation.getUsername());
        }
        if (PasswordHasher.verifyPassword(userInformation.getPassword(), requestedUserInformation.getPassword())) {
            loginService.deleteById(userInformation.getId());
        } else {
            throw new Exception("Password is incorrect");
        }
    }
}
