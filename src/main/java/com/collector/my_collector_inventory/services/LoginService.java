package com.collector.my_collector_inventory.services;

import com.collector.my_collector_inventory.PasswordHasher;
import com.collector.my_collector_inventory.exception.UserAlreadyFoundException;
import com.collector.my_collector_inventory.entities.UserEntity;
import com.collector.my_collector_inventory.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private UserRepository userRepository;

    public void registerUser(UserEntity userEntity) throws UserAlreadyFoundException {
        String hashedPassword = PasswordHasher.hashPassword(userEntity.getPassword());
        save(UserEntity.builder().username(userEntity.getUsername()).password(hashedPassword).build());
    }

    public UserEntity save(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    public UserEntity findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserEntity update(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    public UserEntity findByName(String name) {
        return userRepository.findUserByName(name);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }


}
