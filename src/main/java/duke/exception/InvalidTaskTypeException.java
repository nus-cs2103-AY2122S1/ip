package duke.exception;

public class InvalidTaskTypeException extends DukeException {
    public InvalidTaskTypeException(String task) {
        super(String.format("Incorrect task type for the following task in the file: %s", task));
    }
}
