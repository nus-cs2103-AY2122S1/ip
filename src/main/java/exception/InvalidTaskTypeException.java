package exception;

import type.DukeCommandTypeEnum;

/**
 * Encapsulates an exception when a user inputs an invalid task.
 */
public class InvalidTaskTypeException extends DukeException {
    /**
     * Instantiates an exception when a user inputs an invalid task.
     *
     * @param commandType Command type that is incorrectly used as a task.
     */
    public InvalidTaskTypeException(DukeCommandTypeEnum commandType) {
        super(String.format(
                "'%s' is not a valid task type. "
                        + "Tasks should begin with one of the following: todo, deadline, event",
                commandType
        ));
    }
}
