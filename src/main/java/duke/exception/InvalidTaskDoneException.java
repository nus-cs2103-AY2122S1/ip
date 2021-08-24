package duke.exception;

public class InvalidTaskDoneException extends DukeException {
    public InvalidTaskDoneException() {
        super("OOPS! You are setting a non-existent task as done!");
    }
}
