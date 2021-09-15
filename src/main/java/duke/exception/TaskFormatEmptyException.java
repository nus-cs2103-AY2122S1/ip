package duke.exception;

public class TaskFormatEmptyException extends DukeException {

    public TaskFormatEmptyException() {
        super("The description of a task cannot be empty, please try again");
    }
}
