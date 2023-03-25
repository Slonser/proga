package org.lab5.commands;

import org.lab5.IO.InputClass;
import org.lab5.Validator;
import org.lab5.annotations.CommandInfo;
import org.lab5.collection.CollectionManager;
import org.lab5.models.Flat;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@CommandInfo(mnemonic = "update", message = "", description = "обновить значение элемента коллекции, id которого равен заданному")
public class UpdateCommand implements Command {

    @Override
    public CommandResult execute(List<String> tokens, CollectionManager collectionManager) throws CommandExecutionException {
        if (tokens.size() < 2) {
            return CommandResult.error("Неверное количество аргументов. Использование: update <id>");
        }

        int id = 0;
        try {
            id = Validator.validateAndParse(tokens.get(1), Integer.class);
        } catch (NumberFormatException e) {
            return CommandResult.error("Введено некоректное значение ключа");
        }
        Flat flat = collectionManager.getById(id);
        if (flat == null) {
            return CommandResult.error("Элемент с id=" + id + " не найден");
        }

        BufferedOutputStream writer = new BufferedOutputStream(System.out);
        BufferedInputStream bf = new BufferedInputStream(System.in); //new FileInputStream("./text")
        BufferedReader reader = new BufferedReader(new InputStreamReader(bf, StandardCharsets.UTF_8));
        try {
            flat = InputClass.parse(Flat.class.getName(), new BufferedReader(new InputStreamReader(System.in)), new BufferedOutputStream(System.out), "", Flat.class.getConstructor(), flat);
        } catch (ReflectiveOperationException e) {
            return CommandResult.error("Не удалось обновить элемент с id=" + id);
        }
        return CommandResult.success("Элемент с id=" + id + " успешно обновлен");
    }


    public String getMnemonic() {
        return "update";
    }

    @Override
    public String getDescription() {
        return "обновить значение элемента коллекции, id которого равен заданному";
    }
}
