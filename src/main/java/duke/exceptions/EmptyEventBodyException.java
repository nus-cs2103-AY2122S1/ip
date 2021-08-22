package duke.exceptions;

import duke.messages.MessageConstants;

public class EmptyEventBodyException extends DukeException {
    public EmptyEventBodyException() {
        super(MessageConstants.MESSAGE_EMPTY_EVENT_BODY);
    }
}
