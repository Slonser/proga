package org.lab5.commands;

import org.lab5.IO.OutputClass;
import org.lab5.annotations.CommandInfo;
import org.lab5.collection.CollectionManager;
import org.lab5.models.Flat;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@CommandInfo(mnemonic = "filter_contains_name", message = "", description = "вывести элементы, значение поля name которых содержит заданную подстроку")
public class FilterNameCommand implements Command {
    @Override
    public CommandResult execute(List<String> tokens, CollectionManager collectionManager) throws CommandExecutionException {
        if (tokens.size() != 2) {
            return CommandResult.error("Неверное количество аргументов! " +
                    "Использование: filter_contains_name <подстрока>");
        }

        String substring = tokens.get(1);

        List<Flat> filteredFlats = collectionManager.all().stream()
                .filter(flat -> {
                    return ((Flat) (flat)).getHouse().getName().contains(substring);
                }).toList();
        for (Flat flat : filteredFlats) {
            BufferedOutputStream writer = new BufferedOutputStream(System.out);
            try {
                OutputClass.printClass(flat, writer, "");
            } catch (InvocationTargetException | NoSuchFieldException | ClassNotFoundException | IOException |
                     NoSuchMethodException | IllegalAccessException | InstantiationException e) {
                return CommandResult.error("Попробуйте ещё раз");
            }
        }

        String message = filteredFlats.isEmpty() ? "Элементы не найдены" : "Найдено " + filteredFlats.size() + " элементов";

        return CommandResult.builder()
                .status(CommandResult.Status.OK)
                .message(message)
                .build();
    }

    @Override
    public String getMnemonic() {
        return "filter_contains_name";
    }

    @Override
    public String getDescription() {
        return "вывести элементы, значение поля name которых содержит заданную подстроку";
    }
}


