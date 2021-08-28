package duke.exceptions;

public class InvalidTaskIdException extends DukeException {
    public InvalidTaskIdException() {
        super("I couldn't find a task with that ID. Please check again!");
    }
}
