package com.collector.my_collector_inventory.exception;

public class UserDoesNotExistsException extends RuntimeException {
    public UserDoesNotExistsException(String name) {
        super(String.format("User with name '$s' doesn't exists!", name));
    }
}
