package org.lab5.commands;

import org.lab5.IO.InputClass;
import org.lab5.Validator;
import org.lab5.annotations.CommandInfo;
import org.lab5.collection.CollectionManager;
import org.lab5.models.Flat;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

@CommandInfo(mnemonic = "insert", message = "", description = "добавить новый элемент с заданным ключом")
public class InsertCommand implements Command {

    @Override
    public CommandResult execute(List<String> tokens, CollectionManager collectionManager) throws CommandExecutionException {
        if (tokens.size() < 2) {
            return CommandResult.error("Неверное количество аргументов. Использование: insert <ключ> <элемент>");
        }
        Long key = null;
        try {
            key = Validator.validateAndParse(tokens.get(1), Long.class);
        } catch (NumberFormatException e) {
            return CommandResult.error("Введено некоректное значение ключа");
        }
        if (collectionManager.containsKey(key)) {
            return CommandResult.error("Коллекция уже содержит элемент с таким ключом");
        }
        Flat flat = null;
        try {
            flat = InputClass.parse(Flat.class.getName(), new BufferedReader(new InputStreamReader(System.in)), new BufferedOutputStream(System.out), "", Flat.class.getConstructor(), null);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
        flat.setKey(key);
        collectionManager.insert(key, flat);
        return CommandResult.success("Элемент успешно добавлен в коллекцию.");
    }

    public String getMnemonic() {
        return "insert";
    }

    @Override
    public String getDescription() {
        return "добавить новый элемент с заданным ключом";
    }
}
