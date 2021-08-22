package duke.exception;

public class InvalidTaskNumberException extends DukeException {
    public InvalidTaskNumberException() {
        super("Not a valid task number!");
    }
}
