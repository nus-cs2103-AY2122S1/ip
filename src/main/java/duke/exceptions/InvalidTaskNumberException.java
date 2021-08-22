package duke.exceptions;

import duke.messages.MessageConstants;

public class InvalidTaskNumberException extends DukeException {
    public InvalidTaskNumberException() {
        super(MessageConstants.MESSAGE_INVALID_TASK_NUMBER);
    }
}
