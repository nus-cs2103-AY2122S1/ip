package duke.exceptions;

import duke.messages.MessageConstants;

public class InvalidEventBodyException extends DukeException {
    public InvalidEventBodyException() {
        super(MessageConstants.MESSAGE_INVALID_EVENT_FORMAT);
    }
}
