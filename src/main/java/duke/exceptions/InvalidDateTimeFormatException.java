package duke.exceptions;

import duke.messages.MessageConstants;

public class InvalidDateTimeFormatException extends DukeException {
    public InvalidDateTimeFormatException() {
        super(MessageConstants.MESSAGE_INVALID_DATETIME_FORMAT);
    }
}
