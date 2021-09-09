package duke;

import duke.command.AddCommand;
import duke.command.ArchiveTaskCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.ListArchiveCommand;
import duke.command.ListCommand;
import duke.command.UnarchiveTaskCommand;



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
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                throw new DukeException("OOPS!!! Please enter the task you'd like to "
                        + "mark as done in the following format: \n\t done [task number]");
            }
        case ("delete"):
            try {
                int doneIdx = Integer.parseInt(params[1]) - 1;
                return new DeleteCommand(Integer.parseInt(params[1]) - 1);
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                throw new DukeException("OOPS!!! Please enter the task you'd like to "
                        + "delete in the following format: \n\t done [task number]");
            }
        case ("find"):
            if (params.length == 1) {
                throw new DukeException("OOPS!!! Please enter the keyword you'd like to "
                        + "search for in the following format \n\t find [keyword].");
            }
            return new FindCommand(params[1]);
        case ("archive"):
            try {
                int doneIdx = Integer.parseInt(params[1]) - 1;
                return new ArchiveTaskCommand(doneIdx);
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                throw new DukeException("OOPS!!! Please enter the task you'd like to "
                        + "archive in the following format: \n\t archive [task number]");
            }
        case ("archives"):
            return new ListArchiveCommand();
        case ("unarchive"):
            try {
                int doneIdx = Integer.parseInt(params[1]) - 1;
                return new UnarchiveTaskCommand(doneIdx);
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                throw new DukeException("OOPS!!! Please enter the task you'd like to "
                        + "unarchive in the following format: \n\t unarchive [task number]");
            }
        case ("todo"):
        //fallthrough
        case ("event"):
        //fallthrough
        case ("deadline"):
            return new AddCommand(params);
        case ("bye"):
            return new ByeCommand();
        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
