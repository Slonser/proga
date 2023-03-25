package org.lab5;

import java.lang.reflect.Array;

/**
 * Validator provides methods to validate and parse command-line arguments based on their expected types.
 */
public class Validator {
    /**
     * Parses the given argument and validates that it can be converted to the expected type.
     *
     * @param arg          the argument to validate and parse
     * @param expectedType the expected type of the argument
     * @return the parsed argument, converted to the expected type
     * @throws NumberFormatException    if the argument cannot be parsed as a number
     * @throws IllegalArgumentException if the expected type is not supported
     */
    public static <T> T validateAndParse(String arg, Class<T> expectedType) throws NumberFormatException {
        T res = parse(arg, expectedType);
        return res;
    }

    /**
     * Parses the given arguments and validates that they can be converted to the expected type.
     *
     * @param args         the arguments to validate and parse
     * @param expectedType the expected type of the arguments
     * @return an array of parsed arguments, converted to the expected type
     * @throws NumberFormatException    if any of the arguments cannot be parsed as a number
     * @throws IllegalArgumentException if the expected type is not supported
     */
    public static <T> T[] validateAndParse(String[] args, Class<T> expectedType) throws NumberFormatException {
        T[] result = (T[]) Array.newInstance(expectedType, args.length);
        for (int i = 0; i < args.length; i++) {
            result[i] = validateAndParse(args[i], expectedType);
        }
        return result;
    }

    /**
     * Parses the given argument and converts it to the expected type.
     *
     * @param arg          the argument to parse
     * @param expectedType the expected type of the argument
     * @return the parsed argument, converted to the expected type
     * @throws NumberFormatException    if the argument cannot be parsed as a number
     * @throws IllegalArgumentException if the expected type is not supported
     */
    private static <T> T parse(String arg, Class<T> expectedType) throws NumberFormatException {
        if (expectedType.equals(String.class)) {
            return (T) arg;
        } else if (expectedType.equals(Integer.class)) {
            return (T) Integer.valueOf(arg);
        } else if (expectedType.equals(Double.class)) {
            return (T) Double.valueOf(arg);
        } else if (expectedType.equals(Boolean.class)) {
            return (T) Boolean.valueOf(arg);
        } else if (expectedType.equals(Long.class)) {
            return (T) Long.valueOf(arg);
        } else if (expectedType.equals(Float.class)) {
            return (T) Float.valueOf(arg);
        } else {
            throw new IllegalArgumentException("Unsupported type: " + expectedType);
        }
    }

}
