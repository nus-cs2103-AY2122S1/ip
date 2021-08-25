package duke.parser;

import duke.exception.DukeException;
import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.LostCommand;

public class Parser {

    public static Command parse(String fullCommand) throws DukeException {
        String command = fullCommand.split(" ")[0];
        Command newCommand;

        switch(command) {
        case "exit":
            newCommand = new ExitCommand();
            break;

        case "delete":
            try {
                newCommand = new DeleteCommand(Integer.parseInt(fullCommand.split(" ")[1]));
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                throw new DukeException("OOPS!!! Please enter a valid task number.");
            }
            break;

        case "list":
            newCommand = new ListCommand();
            break;

        case "done":
            try {
                newCommand = new DoneCommand(Integer.parseInt(fullCommand.split(" ")[1]));
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                throw new DukeException("OOPS!!! Please enter a valid task number.");
            }
            break;

        case "todo": case "deadline": case "event":
            newCommand = new AddCommand(command, fullCommand);
            break;

        default:
            newCommand = new LostCommand();
            break;
        }

        return newCommand;
    }

}
