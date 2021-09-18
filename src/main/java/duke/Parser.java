package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.error.DukeException;

/**
 * Deals with making sense of the user command.
 */
public class Parser {

    private static String command;
    private static String description;

    /**
     * Parses the command to return the specified command.
     *
     * @param fullCommand Full command entered by the user.
     * @return The command.
     * @throws DukeException If command is invalid or command format is incorrect.
     */
    public static Command parse(String fullCommand) throws DukeException {
        splitCommand(fullCommand);

        switch (command) {
        case "bye":
            return new ExitCommand();
        case "done":
            return new DoneCommand(getIndex());
        case "list":
            return new ListCommand();
        case "delete":
            return new DeleteCommand(getIndex());
        case "find":
            return new FindCommand(description);
        case "todo":
        case "event":
        case "deadline":
            return new AddCommand(command, description);
        default:
            throw new DukeException("OOPS!! I'm sorry, but I don't know what that means :-(" +
                    "\n Try one of the commands: todo, deadline, event, list, find, done, delete, bye.");
        }
    }

    /**
     * Returns the index specified from the user input.
     *
     * @return The index of the specified task
     * @throws DukeException If there is no specified index or the input is not a number.
     */
    private static int getIndex() throws DukeException {
        int index;

        if (description.equals("")) {
            throw new DukeException("OOPS!! " + command + " needs the index of the task.");
        }

        try {
            index = Integer.parseInt(description);
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!! the index needs to be a number.");
        }

        return index;
    }

    /**
     * Splits the full command in two parts, the command and the description.
     *
     * @param fullCommand Full command.
     */
    private static void splitCommand(String fullCommand) {
        if (fullCommand.contains(" ")) {
            int spaceIdx = fullCommand.indexOf(" ");
            command = fullCommand.substring(0, spaceIdx).toLowerCase();
            description = fullCommand.substring(spaceIdx + 1);
        } else {
            command = fullCommand.toLowerCase();
        }
    }
}
