package org.lab5.CSV;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import org.lab5.Validator;
import org.lab5.models.House;

/**
 * A custom OpenCSV field converter for converting between House objects and strings.
 * Uses OpenCSV annotations to populate the fields of the House object when converting from a string,
 * and converts a House object to a semicolon-delimited string for writing to a CSV file.
 */
public class HouseConverter extends AbstractBeanField<House, String> {

    /**
     * Converts a semicolon-delimited string to a House object, populating the fields of the object
     * using the OpenCSV annotations.
     * @param value the string value to convert
     * @return a House object with fields populated from the input string, or null if the string is invalid
     * @throws CsvDataTypeMismatchException if the input value is of an incorrect type
     * @throws CsvConstraintViolationException if the input value violates a CSV constraint
     */
    @Override
    protected House convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        String[] parts = value.split(";");
        House house = new House();

        // Populate the fields of the house object using the OpenCSV annotations
        try {
            house.setName(Validator.validateAndParse(parts[0], String.class));
        } catch (NumberFormatException e) {
            return null;
        }
        try {
            house.setYear(Validator.validateAndParse(parts[1], Long.class));
        } catch (NumberFormatException e) {
            return null;
        }
        try {
            house.setNumberOfFlatsOnFloor(Validator.validateAndParse(parts[2], Integer.class));
        } catch (NumberFormatException e) {
            return null;
        }
        try {
            house.setNumberOfFloors(Validator.validateAndParse(parts[3], Long.class));
        } catch (NumberFormatException e) {
            return null;
        }

        return house;
    }

    /**
     * Converts a House object to a semicolon-delimited string for writing to a CSV file.
     * @param value the House object to convert
     * @return a semicolon-delimited string representing the House object, or null if the input is not a House object
     * @throws CsvDataTypeMismatchException if the input value is of an incorrect type
     */
    @Override
    protected String convertToWrite(Object value) throws CsvDataTypeMismatchException {
        if (value instanceof House house) {
            String sb = house.getName() + ";" +
                    house.getYear() + ";" +
                    house.getNumberOfFloors() + ";" +
                    house.getNumberOfFlatsOnFloor() + ";";
            return sb;
        } else {
            throw new CsvDataTypeMismatchException("Value is not of type House");
        }
    }

}
