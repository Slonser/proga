package org.lab5.commands;


import org.lab5.annotations.CommandInfo;
import org.lab5.collection.CollectionManager;

import java.util.List;

@CommandInfo(mnemonic = "info", message = "", description = "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)")
public class InfoCommand implements Command {


    @Override
    public CommandResult execute(List<String> args, CollectionManager collectionManager) {
        String info = collectionManager.getCollectionInfo();
        return CommandResult.builder()
                .status(CommandResult.Status.OK)
                .message(info)
                .build();
    }


    public String getMnemonic() {
        return "info";
    }

    @Override
    public String getDescription() {
        return "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }
}
