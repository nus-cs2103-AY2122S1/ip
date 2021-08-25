package duke.ui;

import duke.exception.DukeException;
import duke.command.*;

public class Parser { //The Parser to handle the inputs from the terminal

    //The below function converts the input to the proper command and returns an error if the input is not valid
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

        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
