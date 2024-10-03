package org.example.exceptions;

public class MissingElementException extends RuntimeException{
    public MissingElementException(String message) {
        super(message);
    }
}
