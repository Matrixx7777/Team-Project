package com.kodilla.ecommercee.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("Could not find user with given id");
    }
}
