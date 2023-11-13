package com.manager.smartbackend.infra.exception;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String message) {
        super(message);
    }

    public AlreadyExistsException() {
        super("Already exists!");
    }
}