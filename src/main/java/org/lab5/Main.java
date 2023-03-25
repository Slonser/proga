package org.lab5;

import org.lab5.cli.CLI;
import org.lab5.collection.CollectionManager;
import org.lab5.commands.CommandManager;
import org.lab5.configs.config;
import org.lab5.exceptions.CollectionLoadingException;
import org.lab5.exceptions.EnviromentNotFound;

import java.io.BufferedInputStream;


public class Main {
    public static void main(String[] args) {
        Initiliazation initiliazation = new Initiliazation(config.collection_path, "org.lab5.commands");
        BufferedInputStream bf = new BufferedInputStream(System.in); //new FileInputStream("./text")
        CLI cli = null;
        try {
            CollectionManager collectionManager = initiliazation.initializeCollection();
            CommandManager commandManager = initiliazation.initializeCommands();
            cli = new CLI(commandManager, collectionManager);
        } catch (CollectionLoadingException e) {
            throw new RuntimeException(e);
        } catch (EnviromentNotFound e) {
            System.out.println(e.getMessage());
            return;
        }
        cli.run();
    }
}