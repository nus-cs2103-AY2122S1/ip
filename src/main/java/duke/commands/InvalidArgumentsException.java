package duke.commands;

import duke.DukeException;

public class InvalidArgumentsException extends DukeException {
    public InvalidArgumentsException(String expectedFormat) {
        super("Invalid arguments! Please use the command in the following format:\n\t" + expectedFormat);
    }
}
