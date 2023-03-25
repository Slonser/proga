package org.lab5;

import org.lab5.exceptions.CommandExecutionException;

import java.util.HashSet;
import java.util.Set;

/**
 * A utility class that manages the execution of scripts and prevents recursive loops.
 */
public class ScriptExecutionManager {
    private static final Set<String> executingScripts = new HashSet<>();

    /**
      * Adds the specified script name to the set of executing scripts.
      * If the script name is already in the set, returns false.
      * @param scriptName the name of the script to add to the set
      * @return true if the script name was added successfully, false if it is already in the set
     */
    public static boolean addExecutingScript(String scriptName) {
        if (executingScripts.contains(scriptName)) {
            return false;
        }
        executingScripts.add(scriptName);
        return true;
    }

    /**
      * Removes the specified script name from the set of executing scripts.
      * @param scriptName the name of the script to remove from the set
     */
    public static void removeExecutingScript(String scriptName) {
        executingScripts.remove(scriptName);
    }
}
