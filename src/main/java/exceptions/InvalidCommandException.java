package exceptions;

import messages.MessageConstants;

public class InvalidCommandException extends DukeException {
    public InvalidCommandException() {
        super(MessageConstants.INVALID_COMMAND_MESSAGE);
    }
}
