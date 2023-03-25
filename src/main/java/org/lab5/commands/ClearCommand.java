package org.lab5.commands;

import org.lab5.annotations.CommandInfo;
import org.lab5.collection.CollectionManager;

import java.util.List;

@CommandInfo(mnemonic = "clear", message = "Коллекция очищена", description = "очистить коллекцию")
public class ClearCommand implements Command {

    @Override
    public CommandResult execute(List<String> args, CollectionManager collectionManager) {
        collectionManager.clear();
        System.out.println("Collection cleared");
        return null;
    }

    @Override
    public String getMnemonic() {
        return "clear";
    }

    @Override
    public String getDescription() {
        return "очистить коллекцию";
    }
}

