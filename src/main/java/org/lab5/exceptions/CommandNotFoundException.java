package org.lab5.exceptions;

public class CommandNotFoundException extends CommandExecutionException {
    public CommandNotFoundException(String commandName) {
        super(String.format("Command '%s' not found", commandName));
    }
}
