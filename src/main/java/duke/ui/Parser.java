package duke.ui;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.exception.DukeException;

public class Parser { //The Parser to handle the inputs from the terminal

    /**
     * The below function converts the input to the proper command and returns an error if the input is not valid
     *
     * @param input User input
     * @return the new Command
     * @throws DukeException If the input is not of the right format
     */
    public static Command parse(String input) throws DukeException {
        int spaceIndex = input.indexOf(" ");
        String command;
        String details;
        if (spaceIndex == -1) {
            command = input;
            details = null;
        } else {
            command = input.substring(0, spaceIndex).trim();
            details = input.substring(spaceIndex).trim();
        }
        switch (command) {
        case "bye":
            return new ExitCommand();

        case "todo":
            return new AddCommand(command, details);

        case "deadline":
            return new AddCommand(command, details);

        case "event":
            return new AddCommand(command, details);

        case "done":
            return new DoneCommand(details);

        case "list":
            return new ListCommand();

        case "delete":
            return new DeleteCommand(details);

        case "find":
            return new FindCommand(details);

        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
