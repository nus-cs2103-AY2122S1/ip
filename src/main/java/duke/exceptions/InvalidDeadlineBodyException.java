package duke.exceptions;

import duke.messages.MessageConstants;

public class InvalidDeadlineBodyException extends DukeException {
    public InvalidDeadlineBodyException() {
        super(MessageConstants.MESSAGE_INVALID_DEADLINE_FORMAT);
    }
}
