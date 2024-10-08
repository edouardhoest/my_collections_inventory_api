package com.collector.my_collector_inventory.controller;

import com.collector.my_collector_inventory.PasswordHasher;
import com.collector.my_collector_inventory.entities.UserInformation;
import com.collector.my_collector_inventory.exception.UserAlreadyFoundException;
import com.collector.my_collector_inventory.exception.UserDoesNotExistsException;
import com.collector.my_collector_inventory.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public boolean getUserInformations(@RequestBody UserInformation requestedUserInformation) {
        UserInformation userInformation = loginService.findByUsername(requestedUserInformation.getUsername());
        if (userInformation == null) {
            throw new UserDoesNotExistsException(requestedUserInformation.getUsername());
        }
        return PasswordHasher.verifyPassword(userInformation.getPassword(), requestedUserInformation.getPassword());
    }

    @PostMapping("/add")
    public void addUser(@RequestBody UserInformation requestedUserInformation) {
        UserInformation userInformation = loginService.findByUsername(requestedUserInformation.getUsername());
        if (userInformation != null) {
            throw new UserAlreadyFoundException(userInformation.getUsername());
        }

        loginService.registerUser(requestedUserInformation);
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
