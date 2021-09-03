package duke;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;

public class Parser {
    /**
     * Parses the String representation of the user input and returns
     * the corresponding Command object.
     *
     * @param fullCommand The String representation of the user input.
     * @return The corresponding Command object.
     * @throws DukeException If the user input is invalid.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] params = fullCommand.split(" ", 2);
        String keyword = params[0];

        switch (keyword) {
        case ("list"):
            return new ListCommand();
        case ("done"):
            try {
                int doneIdx = Integer.parseInt(params[1]) - 1;
                return new DoneCommand(doneIdx);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("OOPS!!! Please enter the task you'd like to"
                        + "mark as done in the following format: \n\t done [task number]");
            }
        case ("delete"):
            if (params.length == 1) {
                throw new DukeException("OOPS!!! Please enter the task you'd like to"
                        + "delete in the following format: \n\t delete [task number]");
            }
            return new DeleteCommand(Integer.parseInt(params[1]) - 1);
        case ("find"):
            if (params.length == 1) {
                throw new DukeException("OOPS!!! Please enter the keyword you'd like to "
                        + "search for.");
            }
            return new FindCommand(params[1]);
        //fallthrough
        case ("todo"):
        case ("event"):
        case ("deadline"):
            return new AddCommand(params);
        case ("bye"):
            return new ByeCommand();
        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
