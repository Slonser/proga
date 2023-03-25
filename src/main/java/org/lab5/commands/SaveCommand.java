package org.lab5.commands;

import org.lab5.CSV.CSVserializer;
import org.lab5.annotations.CommandInfo;
import org.lab5.collection.CollectionManager;

import java.util.List;

@CommandInfo(mnemonic = "save", message = "", description = "сохранить коллекцию в файл")
public class SaveCommand implements Command {

    @Override
    public CommandResult execute(List<String> args, CollectionManager collectionManager) {
        if (args.size() < 2) {
            return CommandResult.error("Неверное количество аргументов. Использование: save <файл>");
        }
        CSVserializer.serialize(collectionManager.getCollection(), args.get(1));
        return CommandResult.success("Успешно сохранено в файл");
    }

    @Override
    public String getMnemonic() {
        return "save";
    }

    @Override
    public String getDescription() {
        return "сохранить коллекцию в файл";
    }
}
