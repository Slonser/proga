package org.lab5.commands;

import org.lab5.IO.OutputClass;
import org.lab5.annotations.CommandInfo;
import org.lab5.collection.CollectionManager;
import org.lab5.models.Flat;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;

@CommandInfo(mnemonic = "min_by_id", message = "", description = "вывести любой объект из коллекции, значение поля id которого является минимальным")
public class MinByIdCommand implements Command {
    @Override
    public CommandResult execute(List<String> tokens, CollectionManager collectionManager) throws CommandExecutionException {
        if (collectionManager.isEmpty()) {
            return CommandResult.error("Коллекция пуста");
        }
        BufferedOutputStream writer = new BufferedOutputStream(System.out);
        Flat minFlat = (Flat) Collections.min(collectionManager.all());
        try {
            OutputClass.printClass(minFlat, writer, "");
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException |
                 IOException | ClassNotFoundException | NoSuchFieldException e) {
            return CommandResult.error("Ошибка вывода");
        }
        return CommandResult.success("");
    }

    public String getMnemonic() {
        return "min_by_id";
    }

    @Override
    public String getDescription() {
        return "вывести любой объект из коллекции, значение поля id которого является минимальным";
    }
}
