package duke.exceptions;

import duke.messages.MessageConstants;

/**
 * Represents an <code>Exception</code> due to unsupported format of <code>Event</code> data String provided.
 *
 * @author kevin9foong
 */
public class InvalidEventBodyException extends DukeException {
    public InvalidEventBodyException() {
        super(MessageConstants.INVALID_EVENT_FORMAT_MESSAGE);
    }
}
