package duke.exceptions;

import duke.messages.MessageConstants;

/**
 * Represents an <code>Exception</code> due to unsupported format of <code>Deadline</code> data String provided.
 *
 * @author kevin9foong
 */
public class InvalidDeadlineBodyException extends DukeException {
    public InvalidDeadlineBodyException() {
        super(MessageConstants.MESSAGE_INVALID_DEADLINE_FORMAT);
    }
}
