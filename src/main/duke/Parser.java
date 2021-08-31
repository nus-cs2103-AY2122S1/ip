package duke;

import duke.command.Command;
import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;

/**
 * Parse user responses to Duke chatbot.
 */
public class Parser {

    /**
     * Returns command according to input response.
     * @param fullCommand Unedited user response.
     * @return requested command.
     * @throws DukeException If raised by the command during further parsing.
     */
    public static Command parse(String fullCommand) throws DukeException{
        Command c;
        switch (fullCommand.split(" ")[0]) {
        case "bye":
            c = new ExitCommand();
            break;
        case "list":
            c = new ListCommand(fullCommand);
            break;
        case "done":
            c = new DoneCommand(fullCommand);
            break;
        case "delete":
            c = new DeleteCommand(fullCommand);
            break;
        default:
            c = new AddCommand(fullCommand);              
        }
        return c;
    }
}
