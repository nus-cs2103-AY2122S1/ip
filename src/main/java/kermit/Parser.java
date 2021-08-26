package kermit;

import kermit.command.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Parses user commands for Kermit.
 */
public class Parser {
    private static final String invalidCommandText = "I'm sorry, but I don't know what that means :-(";

    /**
     * Parses user commands for Kermit and determine next action.
     *
     * @param fullCommand user input to be parsed.
     * @return Command that is executable based on user input.
     * @throws KermitException if invalid command is provided.
     */
    public static Command parse(String fullCommand) throws KermitException {
        String command = "";
        String flag;
        String word;

        StringBuilder argumentBuilder = new StringBuilder();
        StringBuilder flagBuilder = new StringBuilder();

        // kermit.command.Task description and flag should be separated by some /kermit.command
        String[] userInput = fullCommand.split("/");
        String commandString = userInput[0];
        String flagString = userInput.length > 1 ? userInput[1] : "";

        String[] commandArr = commandString.split(" ");
        String[] flagArr = flagString.split(" ");

        // first item is kermit.command
        command = commandArr[0];
        flag = flagArr[0];

        String argument = "";
        String dateString = "";

        // Clear contents of string builders
        argumentBuilder.setLength(0);
        flagBuilder.setLength(0);

        // Get description of task and error check
        for (int i = 1; i < commandArr.length; i++) {
            word = commandArr[i];
            if (i != 1) {
                argumentBuilder.append(" ");
            }
            argumentBuilder.append(word);
        }
        argument = argumentBuilder.toString();

        // Get the flags provided for task and error check
        for (int i = 1; i < flagArr.length; i++) {
            word = flagArr[i];
            if (i != 1) {
                flagBuilder.append(" ");
            }
            flagBuilder.append(word);
        }
        dateString = flagBuilder.toString();

        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListTasksCommand();
        case "done":
            return new CompleteTaskCommand(argument);
        case "delete":
            return new DeleteTaskCommand(argument);
        case "todo":
            return new AddTaskCommand("todo", argument, dateString);
        case "event":
            return new AddTaskCommand("event", argument, dateString);
        case "deadline":
            return new AddTaskCommand("deadline", argument, dateString);
        case "find":
            return new FindCommand(argument);
        default:
            throw new KermitException(invalidCommandText);
        }
    }
}