package com.collector.my_collector_inventory.services;

import com.collector.my_collector_inventory.PasswordHasher;
import com.collector.my_collector_inventory.entities.UserInformation;
import com.collector.my_collector_inventory.exception.UserAlreadyFoundException;
import com.collector.my_collector_inventory.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private UserRepository userRepository;

    public void registerUser(UserInformation userInformation) throws UserAlreadyFoundException {
        String hashedPassword = PasswordHasher.hashPassword(userInformation.getPassword());
        save(UserInformation.builder().username(userInformation.getUsername()).password(hashedPassword).build());
    }

    public UserInformation save(UserInformation userInformation) {
        return userRepository.save(userInformation);
    }

    public UserInformation findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserInformation update(UserInformation userInformation) {
        return userRepository.save(userInformation);
    }

    public UserInformation findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }


}
