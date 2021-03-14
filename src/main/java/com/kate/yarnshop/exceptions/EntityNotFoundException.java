package com.kate.yarnshop.exceptions;

public class EntityNotFoundException extends Exception {
    public EntityNotFoundException(String entityName) {
        super(entityName + " entity" + " not found");
    }
}
