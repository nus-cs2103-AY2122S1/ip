package duke.exceptions;

import duke.messages.MessageConstants;

/**
 * Represents an <code>Exception</code> due to missing description or venue in the String
 * representation of <code>Event</code> data.
 *
 * @author kevin9foong
 */
public class EmptyEventBodyException extends DukeException {
    public EmptyEventBodyException() {
        super(MessageConstants.EMPTY_EVENT_BODY_MESSAGE);
    }
}
