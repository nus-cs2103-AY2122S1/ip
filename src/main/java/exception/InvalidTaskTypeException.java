package exception;

import type.DukeCommandTypeEnum;

/**
 * Encapsulates the exception thrown when a user inputs an invalid message that is not a task.
 */
public class InvalidTaskTypeException extends DukeException {
    /**
     * Constructor to instantiate an `exception.InvalidTaskTypeException`
     *
     * @param actionType the invalid input by the user
     */
    public InvalidTaskTypeException(DukeCommandTypeEnum actionType) {
        super(String.format(
            "'%s' is not a valid task type. " +
            "Tasks should begin with one of the following: todo, deadline, event",
            actionType
        ));
    }
}
