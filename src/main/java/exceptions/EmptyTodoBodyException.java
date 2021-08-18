package exceptions;

import messages.MessageConstants;

public class EmptyTodoBodyException extends DukeException {
    public EmptyTodoBodyException() {
        super(MessageConstants.EMPTY_TODO_BODY_MESSAGE);
    }
}

