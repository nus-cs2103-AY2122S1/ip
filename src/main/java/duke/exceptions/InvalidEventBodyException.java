package duke.exceptions;

import duke.messages.MessageConstants;

public class InvalidEventBodyException extends DukeException {
    public InvalidEventBodyException() {
        super(MessageConstants.INVALID_EVENT_FORMAT_MESSAGE);
    }
}
