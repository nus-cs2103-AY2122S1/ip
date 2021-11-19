package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EndCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;




/**
 * A Parser class to parse the input given by the user to their respective commands.
 */
public class Parser {

    /**
     * A parse method that takes in the user input and returns the command based on
     * the user input.
     *
     * @param inputText The user input in command line.
     * @return The command that is with respect to the user input.
     * @throws DukeException In the event that the user input is invalid.
     */
    public static Command parse(String... inputText) throws DukeException {
        assert(inputText.length > 0);
        if (inputText.length == 1) {
            switch (inputText[0]) {
            case "bye":
                return new EndCommand();
            case "list":
                return new ListCommand();
            case "delete":
                throw new DukeException("empty delete");
            case "todo":
                throw new DukeException("empty todo");
            case "deadline":
                throw new DukeException("empty deadline");
            case "event":
                throw new DukeException("empty event");
            case "find":
                throw new DukeException("empty find");
            default:
                throw new DukeException("invalid input");
            }
        } else {
            switch (inputText[0]) {
            case "todo":
            case "deadline":
            case "event":
            case "doafter":
                return new AddCommand(inputText[1], inputText[0]);
            case "done":
                return new DoneCommand(inputText[1]);
            case "delete":
                return new DeleteCommand(inputText[1]);
            case "find":
                return new FindCommand(inputText[1]);
            default:
                throw new DukeException("invalid input");
            }
        }
    }
}
