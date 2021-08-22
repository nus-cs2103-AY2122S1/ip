package duke.exceptions;

import duke.messages.MessageConstants;

/**
 * Represents an <code>Exception</code> due to no tasks being found associated with provided task number.
 *
 * @author kevin9foong
 */
public class InvalidTaskNumberException extends DukeException {
    public InvalidTaskNumberException() {
        super(MessageConstants.INVALID_TASK_NUMBER_MESSAGE);
    }
}
