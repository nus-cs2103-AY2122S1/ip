package duke.exception;

public class DukeOutOfBoundsException extends DukeException {
    public DukeOutOfBoundsException() {
        super("That task doesn't exist.");
    }
}
