package duke.exceptions;

public class OutOfBoundException extends DukeException {
    public OutOfBoundException(int end) {
        super("Please enter an index number between 1 to " + end);
    }
}
