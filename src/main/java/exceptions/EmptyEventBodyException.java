package exceptions;

import messages.MessageConstants;

public class EmptyEventBodyException extends DukeException {
    public EmptyEventBodyException() {
        super(MessageConstants.EMPTY_EVENT_BODY_MESSAGE);
    }
}
