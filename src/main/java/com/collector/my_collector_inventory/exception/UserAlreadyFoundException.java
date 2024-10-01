package com.collector.my_collector_inventory.exception;

public class UserAlreadyFoundException extends RuntimeException {

    public UserAlreadyFoundException(String name) {
        super(String.format("User with name '$s' already exists", name));
    }
}
