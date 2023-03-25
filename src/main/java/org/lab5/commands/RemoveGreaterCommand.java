package org.lab5.commands;

import org.lab5.Validator;
import org.lab5.annotations.CommandInfo;
import org.lab5.collection.CollectionManager;

import java.util.List;

@CommandInfo(mnemonic = "remove_greater_key", message = "", description = "удалить из коллекции все элементы, ключ которых превышает заданный")
public class RemoveGreaterCommand implements Command {
    @Override
    public CommandResult execute(List<String> tokens, CollectionManager collectionManager) throws CommandExecutionException {
        if (tokens.size() < 2) {
            return CommandResult.error("Неверное количество аргументов. Использование: remove_greater_key <ключ>");
        }
        int key = 0;
        try {
            key = Validator.validateAndParse(tokens.get(1), Integer.class);
        } catch (NumberFormatException e) {
            return CommandResult.error("Введено некоректное значение ключа");
        }
        int count = collectionManager.removeGreater(key);
        return CommandResult.builder().status(CommandResult.Status.OK).message("Удалено элементов: " + count).build();
    }

    @Override
    public String getMnemonic() {
        return "remove_greater_key";
    }

    @Override
    public String getDescription() {
        return "удалить из коллекции все элементы, ключ которых превышает заданный";
    }
}

