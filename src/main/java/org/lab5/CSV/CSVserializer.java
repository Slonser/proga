package org.lab5.CSV;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.lab5.models.Flat;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.HashMap;

/**
 * A class for serializing a HashMap of Flat objects to a CSV file.
 */
public class CSVserializer {

    /**
     * Serializes the provided HashMap of Flat objects to a CSV file at the specified path.
     * @param arg the HashMap of Flat objects to serialize
     * @param path the path of the CSV file to create
     */
    public static void serialize(HashMap<Integer, Flat> arg, String path) {
        try {
            Writer writer = new FileWriter(path);
            StatefulBeanToCsv<Flat> beanToCsv = new StatefulBeanToCsvBuilder<Flat>(writer)
                    .withSeparator(';')
                    .build();


            beanToCsv.write(Arrays.asList(arg.values().toArray(new Flat[arg.size()])));

            writer.flush();
            writer.close();
        } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        }
    }
}
