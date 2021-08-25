package duke;

public class TaskDescriptionException extends DukeException{

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
        return super.toString() + "Sorry can't have missing description for task!";
    }
}
