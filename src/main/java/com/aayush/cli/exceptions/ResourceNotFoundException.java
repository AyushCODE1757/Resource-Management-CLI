package com.aayush.cli.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String id) {
        super("Resource not found with ID: " + id);
    }
}