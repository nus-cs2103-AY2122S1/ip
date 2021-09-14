package agent.exceptions;

import agent.messages.MessageConstants;

/**
 * Represents an <code>Exception</code> due to missing text filter when issuing <code>FindTaskCommand</code>.
 *
 * @author kevin9foong
 */
public class EmptyFindBodyException extends DukeException {
    public EmptyFindBodyException() {
        super(MessageConstants.MESSAGE_EMPTY_TEXT_FILTER);
    }
}
