package duke.exceptions;

public class EmptyDescriptionException extends DukeException {

    /**
     * EmptyDescriptionException constructor.
     */
    public EmptyDescriptionException() {
        super("The description of a task cannot be empty!");
    }
}
