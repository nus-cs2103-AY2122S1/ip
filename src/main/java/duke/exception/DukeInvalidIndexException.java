package duke.exception;

public class DukeInvalidIndexException extends DukeException {
    public DukeInvalidIndexException() {
        super("Please enter a positive number starting from 1!");
    }
}
