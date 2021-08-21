package exception;

import type.DukeCommandTypeEnum;

/**
 * Encapsulates an exception when a user inputs an invalid task.
 */
public class InvalidTaskTypeException extends DukeException {
    public InvalidTaskTypeException(DukeCommandTypeEnum actionType) {
        super(String.format(
            "'%s' is not a valid task type. " +
            "Tasks should begin with one of the following: todo, deadline, event",
            actionType
        ));
    }
}
