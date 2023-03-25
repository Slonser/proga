package org.lab5.exceptions;

public class CollectionLoadingException extends Exception {
    /**
     * Constructs a new CollectionLoadingException with the specified detail message.
     *
     * @param message the detail message
     */
    public CollectionLoadingException(String message) {
        super(message);
    }

    /**
     * Constructs a new CollectionLoadingException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause   the cause
     */
    public CollectionLoadingException(String message, Throwable cause) {
        super(message, cause);
    }
}

