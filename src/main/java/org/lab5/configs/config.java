package org.lab5.configs;

import org.lab5.annotations.CustomConfig;
import org.lab5.annotations.FromEnviroment;
/**
 * A class representing a custom configuration.
 */
@CustomConfig
public class config {
    /**
     * The path to the collection, loaded from the environment variable CONF_PATH if available, or "flat.csv" otherwise.
     */
    @FromEnviroment(key = "CONF_PATH")
    static public String collection_path;
}
