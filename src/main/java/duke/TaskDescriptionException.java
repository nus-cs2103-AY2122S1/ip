package duke;

public class TaskDescriptionException extends DukeException {

    TaskDescriptionException() {
        super();
    }

    /**
     * Return string message specific for the exception.
     *
     * @return String message.
     */
    @Override
    public String toString() {
        return super.toString() + "Can't have missing description for task!";
    }
}
