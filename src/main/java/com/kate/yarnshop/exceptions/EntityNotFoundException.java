package com.kate.yarnshop.exceptions;

public class EntityNotFoundException extends Exception {
    public EntityNotFoundException(Long id, String entityName) {
        super("entity " + entityName + " with id " + id + " not found");
    }
}
