package agent.exceptions;

import agent.messages.MessageConstants;

/**
 * Represents an <code>Exception</code> due to missing description or due date in the String
 * representation of <code>Deadline</code> data.
 *
 * @author kevin9foong
 */
public class EmptyDeadlineBodyException extends DukeException {
    public EmptyDeadlineBodyException() {
        super(MessageConstants.MESSAGE_EMPTY_DEADLINE_BODY);
    }
}
