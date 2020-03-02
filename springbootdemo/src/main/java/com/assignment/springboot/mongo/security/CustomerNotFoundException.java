package com.assignment.springboot.mongo.security;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(String string) {
        super("Customer id not found : " + string);
    }

}