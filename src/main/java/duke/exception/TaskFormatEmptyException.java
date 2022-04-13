package duke.exception;

/** Exception to be thrown in the creation of a Task when description is not provided */
public class TaskFormatEmptyException extends DukeException {

    /**
     * Constructor for a TaskFormatEmptyException
     */
    public TaskFormatEmptyException() {
        super("The description of a task cannot be empty, please try again");
    }
}
