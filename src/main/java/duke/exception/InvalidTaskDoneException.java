package duke.exception;

public class InvalidTaskDoneException extends DukeException {
    public InvalidTaskDoneException() {
        super("OOPS! You are seting a non-existent task as done!");
    }
}
