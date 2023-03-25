package org.lab5.commands;

import org.lab5.collection.CollectionManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CommandParser {
    private final Map<String, Command> commands;

    public CommandParser(Map<String, Command> commands) {
        this.commands = commands;
    }

    public static List<String> parseCommand(String line) {
        List<String> tokens = new ArrayList<>();
        String[] parts = line.trim().split("\\s+");

        for (String part : parts) {
            if (!part.isEmpty()) {
                tokens.add(part);
            }
        }

        return tokens;
    }

    public CommandResult parse(String input, CollectionManager collectionManager) {
        // Split the input into tokens
        List<String> tokens = Arrays.asList(input.trim().split("\\s+"));

        // Check if there are any tokens
        if (tokens.isEmpty()) {
            return CommandResult.error("No command provided");
        }

        // Get the command
        Command command = commands.get(tokens.get(0).toLowerCase());

        // Check if the command exists
        if (command == null) {
            return CommandResult.error(String.format("Unknown command: %s", tokens.get(0)));
        }

        // Execute the command
        try {
            return command.execute(tokens.subList(1, tokens.size()), collectionManager);
        } catch (CommandExecutionException e) {
            return CommandResult.error(e.getMessage());
        }
    }

}
