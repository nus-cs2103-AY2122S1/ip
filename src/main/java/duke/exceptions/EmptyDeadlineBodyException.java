package duke.exceptions;

import duke.messages.MessageConstants;

public class EmptyDeadlineBodyException extends DukeException {
    public EmptyDeadlineBodyException() {
        super(MessageConstants.MESSAGE_EMPTY_DEADLINE_BODY);
    }
}
