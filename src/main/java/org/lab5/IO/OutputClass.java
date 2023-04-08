package org.lab5.IO;

import org.lab5.annotations.Skip;
import org.lab5.scheme.FieldSchema;
import org.lab5.scheme.Schema;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.lab5.IO.InputClass.getDeclaredMethod;

/**
 * Output Helper
 */
public class OutputClass {
    /**
     * Prints the value of a field to a buffered output stream.
     *
     * @param field         the field to print
     * @param writer        the buffered output stream to write to
     * @param description   the description of the field
     * @param obj           the object containing the field
     * @throws IOException                  if an I/O error occurs while writing to the stream
     * @throws NoSuchMethodException        if a matching method is not found
     * @throws InvocationTargetException   if the invoked method throws an exception
     * @throws IllegalAccessException     if the method cannot be accessed
     * @throws ClassNotFoundException     if the class cannot be found
     * @throws InstantiationException     if an object cannot be instantiated
     * @throws NoSuchFieldException        if the field is not found
     */
    static public <T> void printField(FieldSchema field, BufferedOutputStream writer, String description, T obj) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException, InstantiationException, NoSuchFieldException {
        field.fieldReference.setAccessible(true);
        String data;
        String prompt = description + field.getName() + ": ";
        data = prompt + field.fieldReference.get(obj);
        writer.write((data + "\n").getBytes(StandardCharsets.UTF_8));
        writer.flush();
    }
    /**
     * Prints the fields of an object to a buffered output stream.
     *
     * @param obj           the object to print
     * @param writer        the buffered output stream to write to
     * @param description   the description of the object
     * @throws InvocationTargetException   if the invoked method throws an exception
     * @throws InstantiationException     if an object cannot be instantiated
     * @throws IllegalAccessException     if the method cannot be accessed
     * @throws NoSuchMethodException        if a matching method is not found
     * @throws IOException                  if an I/O error occurs while writing to the stream
     * @throws ClassNotFoundException     if the class cannot be found
     * @throws NoSuchFieldException        if the field is not found
     */
    static public <T> void printClass(T obj, BufferedOutputStream writer, String description) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, IOException, ClassNotFoundException, NoSuchFieldException {
        String className = obj.getClass().getName();
        List<FieldSchema> fields = Schema.get(className).getFields();
        for (var field : fields) {
            if (field.fieldReference.isAnnotationPresent(Skip.class))
                continue;
            printField(field, writer, description, obj);
        }
    }
}
