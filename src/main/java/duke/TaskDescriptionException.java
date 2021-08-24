package duke;

public class TaskDescriptionException extends DukeException{

    TaskDescriptionException() {
        super();
    }

    /**
     * Return string message specific for the exception.
     *
     * @return string message.
     */
    @Override
    public String toString() {
        return super.toString() + "Sorry can't have missing description for task!";
    }
}
