package com.falabella.sales.users.domain.exceptions;

public class UserDuplicatedException extends Exception {
    public UserDuplicatedException(String message) {
        super(message);
    }
}
