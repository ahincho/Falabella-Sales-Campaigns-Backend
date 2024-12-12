package com.falabella.sales.files.domain.exceptions;

public class FileDuplicatedException extends Exception {
    public FileDuplicatedException(String message) {
        super(message);
    }
}
