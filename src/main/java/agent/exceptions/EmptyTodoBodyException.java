package agent.exceptions;

import agent.messages.MessageConstants;

/**
 * Represents an <code>Exception</code> due to missing description in the String
 * representation of <code>ToDo</code> data.
 *
 * @author kevin9foong
 */
public class EmptyTodoBodyException extends DukeException {
    public EmptyTodoBodyException() {
        super(MessageConstants.MESSAGE_EMPTY_TODO_BODY);
    }
}

