package org.lab5.CSV;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import org.lab5.Validator;
import org.lab5.models.Coordinates;

/**
 * A converter for reading and writing {@link Coordinates} objects in CSV files using OpenCSV.
 */
public class CoordinatesConverter extends AbstractBeanField<Coordinates, String> {

    /**
     * Converts the input String value to a Coordinates object.
     * The String must be in the format "x;y", where x is an integer and y is a float.
     *
     * @param value the input String value to convert
     * @return a new Coordinates object with the x and y values parsed from the input String,
     *         or null if the input String is null, has an incorrect format or cannot be parsed
     * @throws CsvDataTypeMismatchException if the input String value cannot be converted to a Coordinates object
     * @throws CsvConstraintViolationException if the input String value does not meet the expected constraints
     */
    @Override
    protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        if (value == null) {
            return null;
        }
        String[] parts = value.split(";");
        if (parts.length != 2) {
            return null;
        }
        try {
            int x = Validator.validateAndParse(parts[0], Integer.class);
            float y = Validator.validateAndParse(parts[1], Float.class);
            return new Coordinates(x, y);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Converts the input Coordinates object to a String.
     * The resulting String is in the format "x;y", where x is the x-coordinate (an integer)
     * and y is the y-coordinate (a float).
     *
     * @param value the input Coordinates object to convert
     * @return a String representing the Coordinates object, formatted as "x;y"
     * @throws CsvDataTypeMismatchException if the input value is not a Coordinates object
     */
    @Override
    protected String convertToWrite(Object value) throws CsvDataTypeMismatchException {
        if (value == null) {
            throw new CsvDataTypeMismatchException("Value is null");
        }
        if (!(value instanceof Coordinates coordinates)) {
            throw new CsvDataTypeMismatchException("Value is not a Coordinates object");
        }
        return coordinates.getX() + ";" + coordinates.getY();
    }
}
