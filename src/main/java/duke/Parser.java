package duke;

import duke.command.Command;
import duke.exception.DukeException;

public class Parser {

    /**
     * Parses a given input string to determine what command should be executed.
     *
     * @param input The given input String.
     * @return The command to be executed.
     * @throws DukeException The exception thrown when the input cannot be parsed.
     */
    public static Command parse(String input) throws DukeException {
        String command = input.split("\\s", 2)[0];

        for (Command.Type type : Command.Type.values()) {
            if (command.matches(type.commandRegex)) {
                return Command.createCommand(type, input);
            }
        }

        throw new DukeException();
    }
}
