package org.lab5.commands;

import org.lab5.IO.OutputClass;
import org.lab5.annotations.CommandInfo;
import org.lab5.collection.CollectionManager;
import org.lab5.models.Flat;

import java.io.BufferedOutputStream;
import java.util.List;

@CommandInfo(mnemonic = "show", message = "", description = "вывести в стандартный поток вывода все элементы коллекции в строковом представлении")
public class ShowCommand implements Command {

    @Override
    public CommandResult execute(List<String> tokens, CollectionManager collectionManager) throws CommandExecutionException {
        try {
            List<Flat> flats = collectionManager.all();
            if (flats.isEmpty()) {
                return CommandResult.success("Коллекция пуста");
            } else {
                BufferedOutputStream writer = new BufferedOutputStream(System.out);
                for (Flat flat : flats) {
                    OutputClass.printClass(flat, writer, "");
                }
                writer.flush();
                return CommandResult.success("");
            }
        } catch (Exception e) {
            return CommandResult.error("Ошибка при выполнении команды show");
        }
    }


    @Override
    public String getMnemonic() {
        return "show";
    }

    @Override
    public String getDescription() {
        return "вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}

