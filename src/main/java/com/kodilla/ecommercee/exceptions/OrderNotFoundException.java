package com.kodilla.ecommercee.exceptions;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException() {
        super("Could not find order with given id");
    }
}
