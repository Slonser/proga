package org.lab5.commands;

import org.lab5.collection.CollectionManager;

import java.util.List;

public interface Command {
    /**
     * Executes the command with the given arguments and returns a CommandResult.
     *
     * @param tokens the arguments passed to the command
     * @return the result of executing the command
     * @throws CommandExecutionException if an error occurs while executing the command
     */
    CommandResult execute(List<String> tokens, CollectionManager collectionManager) throws CommandExecutionException;

    /**
     * Returns the mnemonic for the command, which is a short string used to identify the command.
     *
     * @return the mnemonic for the command
     */
    String getMnemonic();

    /**
     * Returns the description of the command, which is a longer string used to describe what the command does.
     *
     * @return the description of the command
     */
    String getDescription();
}

