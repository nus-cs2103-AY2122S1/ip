package agent.exceptions;

import agent.messages.MessageConstants;

/**
 * Represents an <code>Exception</code> due to no tasks being found associated with provided task number.
 *
 * @author kevin9foong
 */
public class InvalidTaskNumberException extends DukeException {
    public InvalidTaskNumberException() {
        super(MessageConstants.MESSAGE_INVALID_TASK_NUMBER);
    }
}
