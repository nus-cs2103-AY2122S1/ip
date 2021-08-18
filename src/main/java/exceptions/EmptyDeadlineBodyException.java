package exceptions;

import messages.MessageConstants;

public class EmptyDeadlineBodyException extends DukeException {
    public EmptyDeadlineBodyException() {
        super(MessageConstants.EMPTY_DEADLINE_BODY_MESSAGE);
    }
}
