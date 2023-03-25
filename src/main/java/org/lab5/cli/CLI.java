package org.lab5.cli;

import org.lab5.collection.CollectionManager;
import org.lab5.commands.CommandManager;
import org.lab5.commands.CommandResult;
import org.lab5.exceptions.CommandNotFoundException;

import java.util.Scanner;

/**
 * CLI class represents a command-line interface for interacting with the program.
 * It takes in a CommandManager and a CollectionManager instance upon initialization and runs a loop to
 * read user input, execute commands, and print the result to the console.
 */
public class CLI {
    /**
     * The CommandManager object used for executing commands.
     */
    private final CommandManager commandManager;
    /**
     * The CollectionManager object used for managing the collection.
     */
    private final CollectionManager collectionManager;
    /**
     * Constructs a new CLI object with the given CommandManager and CollectionManager objects.
     *
     * @param commandManager   the CommandManager object to use for executing commands
     * @param collectionManager the CollectionManager object to use for managing the collection
     */
    public CLI(CommandManager commandManager, CollectionManager collectionManager) {
        this.commandManager = commandManager;
        this.collectionManager = collectionManager;
    }

    /**
     * Runs the CLI application. It reads user input, executes the command, and prints the result.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("> ");
            String commandString = scanner.nextLine().trim();

            CommandResult result = null;
            try {
                result = commandManager.executeCommand(commandString, collectionManager);
            } catch (org.lab5.commands.CommandExecutionException e) {
                System.out.println(e.getMessage());
                continue;
            } catch (CommandNotFoundException e) {
                System.out.println("Похоже вы ввели некоректное название команды. Используйте help для получения списка команд.");
                continue;
            }
            if (result.getStatus() == CommandResult.Status.OK)
                System.out.println(result.getMessage());
            else
                System.out.println("Произошла ошибка: " + result.getMessage());
        }
    }
}

