package duke.exceptions;

public class EmptyTaskDescriptionException extends DukeException {

    /**
     * Constructor for EmptyTaskDescriptionException that has a specific message for it
     *
     * @param taskName the type of task that is involved with this exception
     */
    public EmptyTaskDescriptionException(String taskName) {
        super(String.format("The description of a %s cannot be empty!", taskName));
    }
}
