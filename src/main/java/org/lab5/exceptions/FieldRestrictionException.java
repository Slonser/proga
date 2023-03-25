package org.lab5.exceptions;


public class FieldRestrictionException extends Exception {

    public FieldRestrictionException(String message) {
        super(message);
    }

    public FieldRestrictionException(String message, Throwable cause) {
        super(message, cause);
    }
}