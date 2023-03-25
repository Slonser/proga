package org.lab5.commands;

import org.lab5.collection.CollectionManager;
import org.lab5.exceptions.CommandNotFoundException;

import java.util.*;


public class CommandManager {
    private final Map<String, Command> commandMap = new HashMap<>();
    public HashMap<String, Command> commands = new HashMap<>();


    public CommandManager() {
    }

    public void registerCommand(Command command, String mnemonic) {
        commands.put(mnemonic, command);
    }

    public CommandResult executeCommand(String input, CollectionManager collectionManager) throws CommandExecutionException, CommandNotFoundException {
        String[] tokens = input.split("\\s+");
        String mnemonic = tokens[0];
        if (!commands.containsKey(mnemonic))
            throw new CommandNotFoundException("Команды не существует");
        Command command = commands.get(mnemonic);
        CommandResult result = command.execute(List.of(tokens), collectionManager);
        CommandHistory.add(mnemonic);
        return result;
    }

    public void registerCommands(Set<Class<?>> class_command) {
        for (var el : class_command) {
            try {
                Command now_cmd = (Command) el.newInstance();
                this.commands.put(now_cmd.getMnemonic(), now_cmd);
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Command getCommand(String mnemonic) throws CommandNotFoundException {
        Command command = commands.get(mnemonic);
        if (command == null) {
            throw new CommandNotFoundException("Command not found: " + mnemonic);
        }
        return command;
    }

    public Collection<Command> getAll() {
        return commands.values();
    }


}

