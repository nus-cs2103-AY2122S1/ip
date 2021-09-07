package duke.exception;

/**
 * This is a customised exception stands for user has input the
 * same task.
 */
public class DuplicateException extends DukeException {
    @Override
    public String getMessage() {
        return "Hi, this task has already existed in your taskList!";
    }
}
