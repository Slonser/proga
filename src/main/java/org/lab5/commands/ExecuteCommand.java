package org.lab5.commands;

import org.lab5.PackageScanner;
import org.lab5.ScriptExecutionManager;
import org.lab5.annotations.CommandInfo;
import org.lab5.collection.CollectionManager;
import org.lab5.exceptions.CommandNotFoundException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@CommandInfo(mnemonic = "execute_script", description = "")
public class ExecuteCommand implements Command {

    @Override
    public CommandResult execute(List<String> tokens, CollectionManager collectionManager) throws CommandExecutionException {
        if (tokens.size() != 2) {
            return CommandResult.error("execute_script: Incorrect number of arguments");
        }

        String filename = tokens.get(1);
        if(!ScriptExecutionManager.addExecutingScript(tokens.get(1)))
            return CommandResult.error("Попытка рекрусивного запуска скриптов");
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            String line;
            CommandManager commandManager = new CommandManager();
            commandManager.registerCommands(PackageScanner.commands);
            while ((line = bufferedReader.readLine()) != null) {
                List<String> commandTokens = CommandParser.parseCommand(line);
                Command command = null;
                try {
                    command = commandManager.getCommand(commandTokens.get(0));
                } catch (CommandNotFoundException e) {
                    continue;
                }
                if (command == null) {
                    return CommandResult.error(String.format("execute_script: Неизвестная команда %s", commandTokens.get(0)));
                }
                command.execute(commandTokens, collectionManager);
            }
            ScriptExecutionManager.removeExecutingScript(filename);
            return new CommandResult(CommandResult.Status.OK, "Выполнение скрипта завершено");
        } catch (IOException e) {
            return CommandResult.error("execute_script: Не получается прочитать файл");
        } catch (CommandExecutionException e) {
            return CommandResult.error("execute_script: Ошибка запуска в скрипте: " + e.getMessage());
        }
    }

    @Override
    public String getMnemonic() {
        return "execute_script";
    }

    @Override
    public String getDescription() {
        return "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.";
    }
}

