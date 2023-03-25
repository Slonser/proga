package org.lab5.commands;

import org.lab5.Validator;
import org.lab5.annotations.CommandInfo;
import org.lab5.collection.CollectionManager;

import java.util.List;

@CommandInfo(mnemonic = "remove_key", message = "", description = "удалить элемент из коллекции по его ключу")
public class RemoveCommand implements Command {
    @Override
    public CommandResult execute(List<String> tokens, CollectionManager collectionManager) {
        if (tokens.size() < 2) {
            return CommandResult.error("Неверное количество аргументов. Использование: remove_key <ключ>");
        }
        long key = 0;
        try {
            key = Validator.validateAndParse(tokens.get(1), Long.class);
        } catch (NumberFormatException e) {
            return CommandResult.error("Введено некоректное значение ключа");
        }
        if (!collectionManager.containsKey(key)) {
            return CommandResult.error("Коллекция не содержит элемента с ключом " + key);
        }
        collectionManager.remove(key);
        return CommandResult.success("Элемент с ключом " + key + " успешно удален из коллекции");
    }

    @Override
    public String getMnemonic() {
        return "remove_key";
    }

    @Override
    public String getDescription() {
        return "удалить элемент из коллекции по его ключу";
    }
}

