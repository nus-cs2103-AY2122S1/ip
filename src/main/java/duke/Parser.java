package duke;

import duke.command.AddTaskCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitDukeCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;


public class Parser {

    /**
     * Parses user input and returns the appropriate Command
     *
     * @param fullCommand user input
     * @return appropriate Command
     */
    public static Command parse(String fullCommand) {
        if (fullCommand.equals("bye")) {
            return new ExitDukeCommand(fullCommand);
        } else if (fullCommand.equals("list")) {
            return new ListCommand(fullCommand);
        } else {
            String[] parsedUserInput = fullCommand.split(" ", 2);
            if (parsedUserInput[0].equals("done")) {
                return new DoneCommand(fullCommand);
            } else if (parsedUserInput[0].equals("find")) {
                return new FindCommand(fullCommand);
            } else if (parsedUserInput[0].equals("delete")) {
                return new DeleteCommand(fullCommand);
            } else if (parsedUserInput[0].equals("todo") || parsedUserInput[0].equals("t")
                    || parsedUserInput[0].equals("deadline") || parsedUserInput[0].equals("d")
                    || parsedUserInput[0].equals("event") || parsedUserInput[0].equals("e")) {
                return new AddTaskCommand(fullCommand);
            } else {
                throw new DukeException("OOWOOPS!!! I'm sowwie, but I don't know what that mweans :-(");
            }
        }
    }
}
