package duke;

import duke.command.AddCommand;
import duke.command.ArchiveTaskCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.HpCommand;
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
        String[] params = fullCommand.trim().split(" ", 2);
        String keyword = params[0];

        //a secret command
        if (keyword.contains(new Ui().getSecretCmdKey())) {
            return new HpCommand();
        }

        switch (keyword) {
        case ("help"):
            return new HelpCommand();
        case ("list"):
            //fallthrough
        case ("ls"):
            boolean isListArchive = params.length > 1
                    && (params[1].equals("-a") || params[1].equals("-archives"));
            if (isListArchive) {
                return new ListArchiveCommand();
            }
            return new ListCommand();
        case ("done"):
            //fallthrough
        case ("d"):
            try {
                int doneIdx = Integer.parseInt(params[1]) - 1;
                return new DoneCommand(doneIdx);
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                throw new DukeException("OOPS!!! Please enter the task you'd like to "
                        + "mark as done in the following format: \n\t done [task number]");
            }
        case ("delete"):
            //fallthrough
        case ("del"):
            try {
                int doneIdx = Integer.parseInt(params[1]) - 1;
                return new DeleteCommand(Integer.parseInt(params[1]) - 1);
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                throw new DukeException("OOPS!!! Please enter the task you'd like to "
                        + "delete in the following format: \n\t done [task number]");
            }
        case ("find"):
            //fallthrough
        case ("f"):
            if (params.length == 1) {
                throw new DukeException("OOPS!!! Please enter the keyword you'd like to "
                        + "search for in the following format \n\t find [keyword].");
            }
            return new FindCommand(params[1]);
        case ("archive"):
            //fallthrough
        case ("a"):
            try {
                int doneIdx = Integer.parseInt(params[1]) - 1;
                return new ArchiveTaskCommand(doneIdx);
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                throw new DukeException("OOPS!!! Please enter the task you'd like to "
                        + "archive in the following format: \n\t archive [task number]");
            }
        case ("unarchive"):
        case ("ua"):
            try {
                int doneIdx = Integer.parseInt(params[1]) - 1;
                return new UnarchiveTaskCommand(doneIdx);
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                throw new DukeException("OOPS!!! Please enter the task you'd like to "
                        + "unarchive in the following format: \n\t unarchive [task number]");
            }
        case ("todo"):
        //fallthrough
        case ("t"):
            //fallthrough
        case ("event"):
        //fallthrough
        case ("e"):
            //fallthrough
        case ("deadline"):
            //fallthrough
        case ("dl"):
            return new AddCommand(params);
        case ("bye"):
            return new ByeCommand();
        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means. \uD83D\uDEAB");
        }
    }
}
