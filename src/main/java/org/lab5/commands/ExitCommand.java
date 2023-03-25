package org.lab5.commands;

import org.lab5.annotations.CommandInfo;
import org.lab5.collection.CollectionManager;

import java.util.List;

import static java.lang.System.exit;

@CommandInfo(mnemonic = "exit", description = "Exit the program")
public class ExitCommand implements Command {

    @Override
    public CommandResult execute(List<String> tokens, CollectionManager collectionManager) {
        exit(0);
        return new CommandResult(CommandResult.Status.OK, "Завершение программы");
    }

    @Override
    public String getMnemonic() {
        return "exit";
    }

    @Override
    public String getDescription() {
        return "Выход из программы";
    }
}

