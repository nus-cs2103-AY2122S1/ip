package duke.commands;

import duke.DukeException;

public class UnknownCommandException extends DukeException {
    private static final String UNKNOWN_COMMAND_MESSAGE = "I didn't understand what you meant by \"%s\".";

    public UnknownCommandException(String command) {
        super(String.format(UNKNOWN_COMMAND_MESSAGE, command));
    }
}
