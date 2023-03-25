package org.lab5.commands;

import org.lab5.Validator;
import org.lab5.annotations.CommandInfo;
import org.lab5.collection.CollectionManager;

import java.util.List;

@CommandInfo(mnemonic = "remove_lower_key", message = "", description = "удалить из коллекции все элементы, ключ которых меньше, чем заданный")
public class RemoveLowerCommand implements Command {
    @Override
    public CommandResult execute(List<String> tokens, CollectionManager collectionManager) throws CommandExecutionException {
        if (tokens.size() < 2) {
            return CommandResult.error("Неверное количество аргументов. Использование: remove_lower_key <ключ>");
        }

        long key = 0;
        try {
            key = Validator.validateAndParse(tokens.get(1), Long.class);
        } catch (NumberFormatException e) {
            return CommandResult.error("Введено некоректное значение ключа");
        }
        long removedCount = collectionManager.removeLower(key);

        return CommandResult.success(String.format("Удалено элементов: %d", removedCount));
    }

    @Override
    public String getMnemonic() {
        return "remove_lower_key";
    }

    @Override
    public String getDescription() {
        return "удалить из коллекции все элементы, ключ которых меньше, чем заданный";
    }
}

