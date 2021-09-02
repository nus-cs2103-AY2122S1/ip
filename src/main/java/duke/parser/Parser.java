package duke.parser;

import duke.commands.*;
import duke.exception.DukeException;

/**
 * Reads the command input and processes it to decipher what actions Duke should carry out.
 */
public class Parser {
    private String command;

    public Parser(String command) {
        this.command = command;
    }

    /**
     * Reads the command and processes the first word to determine what the command type is.
     *
     * @param command Command that is passed into the parser.
     * @return Type of Command to be executed.
     */
    public static Command process(String command) {
        String[] words = command.split(" ");
        String parsed = words[0];
        try {
            switch (parsed) {
                case ("bye"):
                    return new EndCommand(command);
                case ("list"):
                    return new ListCommand(command);
                case ("deadline"):
                    return new AddDeadlineCommand(command);
                case ("event"):
                    return new AddEventCommand(command);
                case ("done"):
                    return new DoneCommand(command);
                case ("delete"):
                    return new DeleteTaskCommand(command);
                case ("find"):
                    return new FindCommand(command);
                case ("todo"):
                    return new AddToDoCommand(command);
                default:
                    return new InvalidCommand(command);
            }
        } catch (DukeException err) {
            return new InvalidCommand(command);
        }

    }

}
