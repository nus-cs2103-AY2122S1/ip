package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;

/**
 * Parse user responses to Duke chatbot.
 */
public class Parser {

    /**
     * Returns command according to input response.
     *
     * @param fullCommand Unedited user response.
     * @return requested command.
     * @throws DukeException If raised by the command during further parsing.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String type = fullCommand.split(" ")[0];
        switch (type) {
        case "bye":
            return new ExitCommand(fullCommand);
        case "list":
            return new ListCommand(fullCommand);
        case "done":
            return new DoneCommand(fullCommand);
        case "delete":
            return new DeleteCommand(fullCommand);
        case "find":
            return new FindCommand(fullCommand);
        default:
            return new AddCommand(fullCommand);
        }
    }
}
