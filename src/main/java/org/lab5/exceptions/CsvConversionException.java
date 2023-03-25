package org.lab5.exceptions;

public class CsvConversionException extends Exception {

    public CsvConversionException(String message) {
        super(message);
    }

    public CsvConversionException(String message, Throwable cause) {
        super(message, cause);
    }
}
