package duke.exceptions;

import duke.messages.MessageConstants;

public class EmptyTodoBodyException extends DukeException {
    public EmptyTodoBodyException() {
        super(MessageConstants.MESSAGE_EMPTY_TODO_BODY);
    }
}

