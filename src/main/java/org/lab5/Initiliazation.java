package org.lab5;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.lab5.IO.InputClass;
import org.lab5.collection.CollectionManager;
import org.lab5.collection.FlatCollectionManager;
import org.lab5.commands.CommandManager;
import org.lab5.configs.config;
import org.lab5.exceptions.CollectionLoadingException;
import org.lab5.exceptions.CommandRegistrationException;
import org.lab5.exceptions.EnviromentNotFound;
import org.lab5.models.Flat;

import java.io.FileReader;
import java.io.Reader;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * The Initialization class is responsible for initializing the collection of flats
 * and the command manager. It uses a PackageScanner object to scan for available
 * commands in the specified package.
 */
public class Initiliazation {
    /**
     * The file path of the collection file to load.
     */
    private final String collectionFilePath;
    /**
     * The package containing the available commands.
     */
    private final String commandsPackage;
    /**
     * The PackageScanner object used for scanning the specified package for commands.
     */
    private final PackageScanner packageScanner;

    /**
     * Constructor for the Initialization class.
     *
     * @param collectionFilePath the file path of the collection file to load
     * @param commandsPackage    the package containing the available commands
     */
    public Initiliazation(String collectionFilePath, String commandsPackage) {
        this.collectionFilePath = collectionFilePath;
        this.commandsPackage = commandsPackage;
        packageScanner = new PackageScanner();
        packageScanner.scanPackage("org.lab5");
        packageScanner.setFromEnvironment(Collections.singleton(config.class));
    }

    /**
     * Initializes the collection of flats by loading the collection file and creating a new FlatCollectionManager object.
     *
     * @return a new FlatCollectionManager object initialized with the loaded flats
     * @throws CollectionLoadingException if the collection file could not be loaded
     * @throws EnviromentNotFound         if the specified environment variable could not be found
     */
    public CollectionManager initializeCollection() throws CollectionLoadingException, EnviromentNotFound {
        Set<Flat> flats;
        if (collectionFilePath == null) {
            System.out.println("Не задан ключ переменной среды, коллекция не будет загружена.");
            flats = new HashSet<>();
        } else {
            flats = loadCollectionFromFile(collectionFilePath);
        }
        flats.removeIf(flat -> flat.getHouse() == null || flat.getCoordinates() == null);
        CollectionManager<Flat> flatCollection = new FlatCollectionManager(flats);
        InputClass.id = flatCollection.getMaxId();
        return flatCollection;
    }
    /**
     * Initializes the command manager by scanning the specified package for available commands and registering them with a new CommandManager object.
     *
     * @return a new CommandManager object initialized with the available commands
     * @throws CommandRegistrationException if a command could not be registered with the CommandManager object
     */
    public CommandManager initializeCommands() throws CommandRegistrationException {
        CommandManager commandManager = new CommandManager();
        commandManager.registerCommands(this.scanPackageForCommands(commandsPackage));
        return commandManager;
    }
    /**
     * Loads the collection of flats from the specified file.
     *
     * @param filePath the file path of the collection file to load
     * @return a set of flats loaded from the specified file
     * @throws CollectionLoadingException if the collection file could not be loaded
     */
    private Set<Flat> loadCollectionFromFile(String filePath) throws CollectionLoadingException {
        try (Reader reader = new FileReader(filePath)) {
            CsvToBean<Flat> csvToBean = new CsvToBeanBuilder<Flat>(reader)
                    .withType(Flat.class)
                    .withSeparator(';')
                    .withIgnoreLeadingWhiteSpace(true)
                    .withIgnoreEmptyLine(true)
                    .build();
            return new HashSet<>(csvToBean.parse());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new HashSet<>();
    }
    /**
     * Scans the specified package for command classes and returns a set of their {@code Class} objects.
     * @param commandsPackage the package to scan for command classes
     * @return a set of {@code Class} objects representing the command classes
     * @throws CommandRegistrationException if an error occurs while scanning the package
     */
    public Set<Class<?>> scanPackageForCommands(String commandsPackage) throws CommandRegistrationException {
        return PackageScanner.commands;
    }
}
