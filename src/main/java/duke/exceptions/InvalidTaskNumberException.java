package duke.exceptions;

import duke.messages.MessageConstants;

public class InvalidTaskNumberException extends DukeException {
    public InvalidTaskNumberException() {
        super(MessageConstants.INVALID_TASK_NUMBER_MESSAGE);
    }
}
