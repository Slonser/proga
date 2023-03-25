package org.lab5.commands;

import org.lab5.IO.OutputClass;
import org.lab5.Validator;
import org.lab5.annotations.CommandInfo;
import org.lab5.collection.CollectionManager;
import org.lab5.models.Flat;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@CommandInfo(mnemonic = "filter_by_number_of_rooms", message = "", description = "вывести элементы, значение поля numberOfRooms которых равно заданному")
public class FilterRoomsCommand implements Command {

    @Override
    public CommandResult execute(List<String> tokens, CollectionManager collectionManager) throws CommandExecutionException {
        if (tokens.size() < 2) {
            return CommandResult.error("Не задано значение поля numberOfRooms");
        }
        int numberOfRooms;
        try {
            numberOfRooms = Validator.validateAndParse(tokens.get(1), Integer.class);
        } catch (NumberFormatException e) {
            return CommandResult.error("Значение поля numberOfRooms должно быть целым числом");
        }
        List<Flat> flats = collectionManager.filterByNumberOfRooms(numberOfRooms);
        String message;
        CommandResult.Status status = CommandResult.Status.OK;
        BufferedOutputStream writer = new BufferedOutputStream(System.out);
        for (Flat flat : flats) {
            try {
                OutputClass.printClass(flat, writer, "");
            } catch (InvocationTargetException | NoSuchFieldException | ClassNotFoundException | IOException |
                     NoSuchMethodException | IllegalAccessException | InstantiationException e) {
                return CommandResult.error("Попробуйте ещё раз");
            }
        }
        if (flats.isEmpty()) {
            message = "Элементов с полем numberOfRooms, равным " + numberOfRooms + ", не найдено";
            status = CommandResult.Status.ERROR;
        } else {
            message = "Найдено " + flats.size() + " элементов";
        }
        return CommandResult.builder()
                .status(status)
                .message(message)
                .build();
    }

    @Override
    public String getMnemonic() {
        return "filter_by_number_of_rooms";
    }

    @Override
    public String getDescription() {
        return "вывести элементы, значение поля numberOfRooms которых равно заданному";
    }
}

