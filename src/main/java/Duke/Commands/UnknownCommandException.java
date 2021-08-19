package Duke.Commands;

import Duke.DukeException;

public class UnknownCommandException extends DukeException {
    private static final String UNKNOWN_COMMAND_MESSAGE = "Sorry I didn't understand what you meant by \"%s\".";

    public UnknownCommandException(String command) {
        super(String.format(UNKNOWN_COMMAND_MESSAGE, command));
    }
}
