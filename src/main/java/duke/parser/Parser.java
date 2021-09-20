package duke.parser;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.command.UpdateCommand;
import duke.exception.DukeException;

public class Parser {

    /**
     * Returns the command inputted by the user.
     *
     * @param input user's input.
     * @return command
     */
    public static Command parse(String input) {
        String[] commandWord = input.split("\\s+");
        Command command;
        switch (commandWord[0]) {
        case "help":
            command = new HelpCommand();
            break;
        case "list":
            command = new ListCommand();
            break;
        case "done":
            command = new DoneCommand();
            break;
        case "delete":
            command = new DeleteCommand();
            break;
        case "todo":
            command = new TodoCommand();
            break;
        case "event":
            command = new EventCommand();
            break;
        case "deadline":
            command = new DeadlineCommand();
            break;
        case "find":
            command = new FindCommand();
            break;
        case "update":
            command = new UpdateCommand();
            break;
        case "bye":
        case "exit":
            command = new ExitCommand();
            break;
        default:
            throw new DukeException(DukeException.Type.InvalidCommand);
        }
        return command;
    }

}
