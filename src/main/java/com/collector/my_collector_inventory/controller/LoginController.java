package com.collector.my_collector_inventory.controller;

import com.collector.my_collector_inventory.PasswordHasher;
import com.collector.my_collector_inventory.exception.UserAlreadyFoundException;
import com.collector.my_collector_inventory.exception.UserDoesNotExistsException;
import com.collector.my_collector_inventory.entities.UserEntity;
import com.collector.my_collector_inventory.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public boolean getSingleManga(@RequestBody UserEntity requestedUserEntity) throws IOException {
        UserEntity userEntity = loginService.findByName(requestedUserEntity.getUsername());
        if (userEntity == null) {
            throw new UserDoesNotExistsException(requestedUserEntity.getUsername());
        }
        return PasswordHasher.verifyPassword(requestedUserEntity.getPassword(), userEntity.getPassword());
    }

    @PostMapping
    public void addUser(@RequestBody UserEntity requestedUserEntity) {
        UserEntity userEntity = loginService.findByName(requestedUserEntity.getUsername());
        if (userEntity != null) {
            throw new UserAlreadyFoundException(userEntity.getUsername());
        }

        loginService.registerUser(requestedUserEntity);
    }

    @PostMapping
    public void deleteUser(@RequestBody UserEntity requestedUserEntity) throws Exception {
        UserEntity userEntity = loginService.findByName(requestedUserEntity.getUsername());
        if (userEntity == null) {
            throw new UserDoesNotExistsException(requestedUserEntity.getUsername());
        }
        if (!PasswordHasher.verifyPassword(requestedUserEntity.getPassword(), userEntity.getPassword())) {
            loginService.deleteById(userEntity.getId());
        } else {
            throw new Exception("Password is incorrect");
        }
    }
}
