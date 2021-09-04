package duke;

import duke.command.Command;
import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.DoneCommand;
import duke.command.ListCommand;
import duke.error.DukeException;

/**
 * Deals with making sense of the user command.
 */
public class Parser {
    /**
     * Parses the command to return the specified command.
     *
     * @param fullCommand Full command entered by the user.
     * @return The command.
     * @throws DukeException If command is invalid or command format is incorrect.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String command;
        String description = "";
        int index;

        if (fullCommand.contains(" ")) {
            int spaceIdx = fullCommand.indexOf(" ");
            command = fullCommand.substring(0, spaceIdx).toLowerCase();
            description = fullCommand.substring(spaceIdx + 1);
        } else {
            command = fullCommand.toLowerCase();
        }

        switch (command) {
            case "bye":
                return new ExitCommand();
            case "done":
                if (description.equals("")) {
                    throw new DukeException("OOPS!! done needs the index of the task.");
                }

                try {
                    index = Integer.parseInt(description);
                } catch (NumberFormatException e) {
                    throw new DukeException("OOPS!! the index needs to be a number.");
                }
                return new DoneCommand(index);
            case "list":
                return new ListCommand();
            case "delete":
                if (description.equals("")) {
                    throw new DukeException("OOPS!! delete needs the index of the task.");
                }
                try {
                    index = Integer.parseInt(description);
                } catch (NumberFormatException e) {
                    throw new DukeException("OOPS!! the index needs to be a number.");
                }
                return new DeleteCommand(index);
            case "find":
                if (description.equals("")) {
                    throw new DukeException("OOPS!! find needs a keyword.");
                }
                return new FindCommand(description);
            case "todo":
            case "event":
            case "deadline":
                if (description.equals("")) {
                    throw new DukeException("OOPS!! the description of " + command + " cannot be empty.");
                }
                return new AddCommand(command, description);
            default:
                throw new DukeException("OOPS!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
