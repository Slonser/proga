package org.lab5.exceptions;

public class EnviromentNotFound extends Exception {
    public EnviromentNotFound(String message) {
        super(message);
    }

    public EnviromentNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}

