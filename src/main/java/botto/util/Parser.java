package botto.util;

import botto.BottoException;
import botto.command.AddDeadlineCommand;
import botto.command.AddEventCommand;
import botto.command.AddToDoCommand;
import botto.command.Command;
import botto.command.DeleteCommand;
import botto.command.ExitCommand;
import botto.command.FindCommand;
import botto.command.HelpCommand;
import botto.command.MarkDoneCommand;
import botto.command.ShowListCommand;


/**
 * This class deals with making sense of the user command
 */
public class Parser {

    /** current available user commands */
    public static final String[] COMMANDS = {"list", "done", "todo", "deadline",
        "event", "delete", "bye", "find", "help"};
    /**
     * analyse user inputs and find out type of commands needed by the user
     *
     * @param fullCommand input from the user
     * @return type of commands requested by the user
     * @throws BottoException when the type of commands cannot be resolved
     */
    public static Command parse(String fullCommand) throws BottoException {
        assert fullCommand != null : "Command is not supposed to be null here";
        String command = findCommand(fullCommand);

        switch (command) {
        case "list":
            return new ShowListCommand();
        case "done":
            return new MarkDoneCommand(fullCommand);
        case "todo":
            return new AddToDoCommand(fullCommand);
        case "deadline":
            return new AddDeadlineCommand(fullCommand);
        case "event":
            return new AddEventCommand(fullCommand);
        case "delete":
            return new DeleteCommand(fullCommand);
        case "bye":
            return new ExitCommand();
        case "find":
            return new FindCommand(fullCommand);
        case "help":
            return new HelpCommand();
        default:
            throw new BottoException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }



    private static String findCommand(String fullCommand) {
        for (String x: COMMANDS) {
            if (fullCommand.startsWith(x)) {
                return x;
            }
        }
        return "";
    }

}
