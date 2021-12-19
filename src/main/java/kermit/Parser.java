package kermit;

import java.util.Arrays;

import kermit.command.AddDeadlineCommand;
import kermit.command.AddEventCommand;
import kermit.command.AddToDoCommand;
import kermit.command.Command;
import kermit.command.CompleteTaskCommand;
import kermit.command.DeleteTaskCommand;
import kermit.command.ExitCommand;
import kermit.command.FindKeywordCommand;
import kermit.command.HelpCommand;
import kermit.command.ListTasksCommand;

/**
 * Parses user commands for Kermit.
 */
public class Parser {
    private static final String invalidCommandText = "I'm sorry, but I don't know what that means :-(";

    /**
     * Constructs a sentence after omitting the first word.
     * The first word is usually the command or the flag
     *
     * @param wordArr Words used to construct sentence
     * @return Sentence after omitting first word
     */
    private static String constructCommandArguments(String[] wordArr) {
        // Handle empty commands or
        // skip over command/ flag
        if (wordArr.length < 2) {
            return "";
        }
        String[] wordArrWithoutCommand = Arrays.copyOfRange(wordArr, 1, wordArr.length);
        return String.join(" ", wordArrWithoutCommand);
    }

    /**
     * Creates kermit command using command word provided by the user.
     * Command is created using the argument and dateString provided.
     *
     * @param command Type of command invoked
     * @param argument argument for command
     * @param dateString date for the command
     * @return Command that can be executed
     * @throws KermitException if invalid arguments are provided to the command or invalid command provided
     */
    private static Command createCommand(String command, String argument, String dateString) throws KermitException {
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
            return new AddToDoCommand(argument);
        case "event":
            return new AddEventCommand(argument, dateString);
        case "deadline":
            return new AddDeadlineCommand(argument, dateString);
        case "find":
            return new FindKeywordCommand(argument);
        case "help":
            return new HelpCommand(argument);
        default:
            throw new KermitException(invalidCommandText);
        }
    }
    /**
     * Parses user commands for Kermit and determine next action.
     *
     * @param fullCommand user input to be parsed.
     * @return Command that is executable based on user input.
     * @throws KermitException if invalid command is provided.
     */
    public static Command parse(String fullCommand) throws KermitException {
        String command;

        // Task description and flag should be separated by some /flag
        // flags are in the form /flag, they are used to specify the deadline
        // of tasks or when events will happen i.e /by or /at
        String[] userInput = fullCommand.split("/");

        // Get description of task
        String commandString = userInput[0];
        String[] commandArr = commandString.split(" ");
        command = commandArr[0];
        String argument = constructCommandArguments(commandArr);

        // Get flag for task
        // Flag denoted by a / is not found in the string, set flagString to blank
        // to let command class error handle
        String flagString = userInput.length > 1 ? userInput[1] : "";
        String[] flagArr = flagString.split(" ");
        String dateString = constructCommandArguments(flagArr);

        return createCommand(command, argument, dateString);
    }
}
