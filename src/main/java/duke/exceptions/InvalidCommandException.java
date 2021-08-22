package duke.exceptions;

import duke.messages.MessageConstants;

public class InvalidCommandException extends DukeException {
    public InvalidCommandException() {
        super(MessageConstants.MESSAGE_INVALID_COMMAND);
    }
}
