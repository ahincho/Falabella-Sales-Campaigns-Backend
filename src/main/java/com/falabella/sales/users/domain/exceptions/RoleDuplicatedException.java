package com.falabella.sales.users.domain.exceptions;

public class RoleDuplicatedException extends Exception {
    public RoleDuplicatedException(String message) {
        super(message);
    }
}
