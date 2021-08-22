package duke.exceptions;

import duke.messages.MessageConstants;

public class InvalidDeadlineBodyException extends DukeException {
    public InvalidDeadlineBodyException() {
        super(MessageConstants.INVALID_DEADLINE_FORMAT_MESSAGE);
    }
}
