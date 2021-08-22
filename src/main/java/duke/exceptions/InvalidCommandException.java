package duke.exceptions;

import duke.messages.MessageConstants;

/**
 * Represents an <code>Exception</code> due to unsupported command provided.
 *
 * @author kevin9foong
 */
public class InvalidCommandException extends DukeException {
    public InvalidCommandException() {
        super(MessageConstants.MESSAGE_INVALID_COMMAND);
    }
}
