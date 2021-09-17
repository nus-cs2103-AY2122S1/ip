package duke.exceptions;

public class EmptyTimestampException extends DukeException {

    /**
     * EmptyTimestampException constructor.
     */
    public EmptyTimestampException() {
        super("The timestamp of a Deadline/Event task cannot be empty!");
    }
}
