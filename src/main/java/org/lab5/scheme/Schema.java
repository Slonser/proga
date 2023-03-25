package org.lab5.scheme;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Schema class provides methods to generate and retrieve schema information for models.
 */
public class Schema {
    /**
     * A HashMap to store the generated schema information for the models.
     */
    private static final HashMap<String, TypeSchema> schemaHashMap = new HashMap<>();
    /**
     * Generate schema information for the given set of classes.
     *
     * @param cls a Set of classes for which schema information needs to be generated.
     */
    public static void modelSchemeGenerator(Set<Class<?>> cls) {
        for (var clazz : cls) {
            List<FieldSchema> fields = new ArrayList<>();
            for (var field : clazz.getDeclaredFields()) {
                FieldSchema fieldSchema = new FieldSchema(field);
                fields.add(fieldSchema);
            }
            TypeSchema typeSchema = new TypeSchema(fields, clazz);
            schemaHashMap.put(clazz.getName(), typeSchema);
        }
    }

    /**
     * Retrieve the schema information for a given class name.
     *
     * @param key the name of the class for which schema information needs to be retrieved.
     * @return the TypeSchema object containing the schema information for the given class name.
     */
    public static TypeSchema get(String key) {
        return schemaHashMap.get(key);
    }
}
