package org.lab5.commands;

import org.lab5.annotations.CommandInfo;
import org.lab5.collection.CollectionManager;

import java.util.List;

@CommandInfo(mnemonic = "history", message = "", description = "вывести последние 6 команд (без их аргументов)")
public class HistoryCommand implements Command {


    public HistoryCommand() {
    }

    @Override
    public CommandResult execute(List<String> args, CollectionManager collectionManager) {
        return CommandResult.builder()
                .status(CommandResult.Status.OK)
                .message(String.join("\n", CommandHistory.getHistory()))
                .build();
    }

    @Override
    public String getMnemonic() {
        return "history";
    }

    @Override
    public String getDescription() {
        return "вывести последние 6 команд (без их аргументов)";
    }
}

