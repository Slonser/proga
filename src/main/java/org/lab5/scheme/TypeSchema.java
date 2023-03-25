package org.lab5.scheme;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
/**
 * A representation of the schema for a given Java class.
 * Contains a list of field schemas, an instance of the class (if it is not an enum),
 * the reference to the class, and the name of the class.
 */
public class TypeSchema {
    /**
     * The list of field schemas for this class schema.
     */
    List<FieldSchema> fields;
    /**
     * The instance of the class represented by this schema, if it is not an enum.
     */
    Object instance = null;
    /**
     * The reference to the class represented by this schema.
     */
    Class classReference;
    /**
     * The name of the class represented by this schema.
     */
    String name;
    /**
     * Creates a new TypeSchema object with the given field schemas and class reference.
     * If the class is not an enum, creates an instance of the class using its default constructor.
     *
     * @param fields the list of field schemas for this class schema
     * @param classReference the reference to the class represented by this schema
     */
    public TypeSchema(List<FieldSchema> fields, Class classReference) {
        this.fields = fields;
        if (!classReference.isEnum()) {
            Constructor<?> constructor = null;
            try {
                constructor = classReference.getConstructor();
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
            constructor.setAccessible(true);
            try {
                this.instance = constructor.newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                System.out.println("Error while creating object instance");
                throw new RuntimeException(e);
            }
        }
        this.classReference = classReference;
        name = classReference.getName();

    }
    /**
     * Returns the list of field schemas for this class schema.
     *
     * @return the list of field schemas for this class schema
     */
    public List<FieldSchema> getFields() {
        return fields;
    }

    /**
     * Returns a clone of the instance of the class represented by this schema, if it is not an enum.
     *
     * @return a clone of the instance of the class represented by this schema
     * @throws RuntimeException if there was an error generating the instance via clone
     */
    public Object getInstance() {
        try {
            return instance.getClass().getMethod("clone").invoke(instance);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.out.println("Failed to generate instance via clone");
            throw new RuntimeException(e);
        }

    }
}
