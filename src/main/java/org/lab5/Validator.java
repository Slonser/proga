package org.lab5;

import com.opencsv.bean.CsvCustomBindByName;
import org.lab5.scheme.FieldSchema;
import org.lab5.scheme.Schema;
import org.lab5.scheme.TypeSchema;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.util.List;

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

    public static String validateField(FieldSchema field, Object arg) throws java.lang.NullPointerException{
        if(field.getFieldReference().isAnnotationPresent(CsvCustomBindByName.class)) {
            try {
                return validateObject(arg.getClass().getName(),arg);
            } catch (IllegalAccessException e) {
                throw new NullPointerException();
            }
        }

        if(arg == null && field.notNull)
            return "Данное поле не может быть пустым\n";
        if (field.min != null && Float.valueOf( arg.toString()) <= field.min)
            return "Введите значение большее чем " + field.min + "\n";
        if (field.max != null && Float.valueOf( arg.toString())> field.max)
            return "Введите значение меньшее или равное чем " + field.max + "\n";
        return null;
    }

    static public String validateObject(String className, Object obj) throws IllegalAccessException,java.lang.NullPointerException {
        TypeSchema schema = Schema.get(className);
        List<FieldSchema> fields = schema.getFields();

        for (var field : fields) {
                String res = validateField(field,field.getFieldReference().get(obj));
                if(res != null)
                    return res;
        }

        return null;
    }

}
