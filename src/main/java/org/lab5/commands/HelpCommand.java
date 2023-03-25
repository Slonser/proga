package org.lab5.commands;

import org.lab5.PackageScanner;
import org.lab5.annotations.CommandInfo;
import org.lab5.collection.CollectionManager;

import java.util.List;

@CommandInfo(mnemonic = "help", message = "", description = "вывести справку по доступным командам")
public class HelpCommand implements Command {


    @Override
    public CommandResult execute(List<String> tokens, CollectionManager collectionManager) {
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("Список доступных команд:\n");

        for (Class<?> commandInfo : PackageScanner.commands) {
            try {
                Command nowCommand = ((Class<Command>) commandInfo).newInstance();
                messageBuilder.append(String.format("\t%s - %s\n", nowCommand.getMnemonic(), nowCommand.getDescription()));

            } catch (InstantiationException | IllegalAccessException e) {
                return CommandResult.error(e.getMessage());
            }
        }

        return CommandResult.builder()
                .status(CommandResult.Status.OK)
                .message(messageBuilder.toString())
                .build();
    }

    @Override
    public String getMnemonic() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "вывести справку по доступным командам";
    }
}

