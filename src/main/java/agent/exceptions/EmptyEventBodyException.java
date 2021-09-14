package agent.exceptions;

import agent.messages.MessageConstants;

/**
 * Represents an <code>Exception</code> due to missing description or venue in the String
 * representation of <code>Event</code> data.
 *
 * @author kevin9foong
 */
public class EmptyEventBodyException extends DukeException {
    public EmptyEventBodyException() {
        super(MessageConstants.MESSAGE_EMPTY_EVENT_BODY);
    }
}
