package agent.exceptions;

import agent.messages.MessageConstants;

/**
 * Represents an <code>Exception</code> due to unsupported format of <code>Event</code> data String provided.
 *
 * @author kevin9foong
 */
public class InvalidEventBodyException extends DukeException {
    public InvalidEventBodyException() {
        super(MessageConstants.MESSAGE_INVALID_EVENT_FORMAT);
    }
}
