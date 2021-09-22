package duke;

import duke.command.AddTaskCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;

/**
 * Class to convert user inputs to Commands.
 */
public class Parser {

    /**
     * Returns appropriate Command based on user input.
     *
     * @param cmd User input.
     * @return A Command to be executed.
     * @throws DukeException Thrown if user input is invalid.
     */
    public static Command parse(String cmd) throws DukeException {
        if (cmd.equals("bye")) {
            return new ByeCommand();
        } else if (cmd.equals("list")) {
            return new ListCommand();
        } else if (cmd.equals("help")) {
            return new HelpCommand();
        } else {
            return parseSpecialCommand(cmd);
        }
    }

    private static Command parseSpecialCommand(String cmd) throws DukeException {
        String[] arr = cmd.split(" ", 2);
        if (validateCommand(arr)) {
            if (arr[0].equals("find")) {
                return new FindCommand(arr[1].trim());
            }
            if (arr[0].equals("done")) {
                return new DoneCommand(arr[1].trim());
            }
            if (arr[0].equals("delete")) {
                return new DeleteCommand(arr[1].trim());
            }
            if (arr[0].equals("todo") || arr[0].equals("event") || arr[0].equals("deadline")) {
                return new AddTaskCommand(arr);
            }
        }
        throw new DukeException("Invalid Command: please try again!");
    }
    private static boolean validateCommand(String[] arr) {
        return arr.length > 1 && arr[1].trim().length() > 0;
    }
}

