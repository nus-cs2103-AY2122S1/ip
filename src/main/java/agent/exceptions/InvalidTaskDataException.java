package agent.exceptions;

import agent.messages.MessageConstants;

/**
 * Represents an <code>Exception</code> due to invalid data representation of <code>Task</code>
 * stored in save file.
 *
 * @author kevin9foong
 */
public class InvalidTaskDataException extends DukeException {
    public InvalidTaskDataException() {
        super(MessageConstants.MESSAGE_INVALID_TASK_DATA);
    }
}
