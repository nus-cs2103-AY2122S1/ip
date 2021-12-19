package duke.exception;

/**
 * A subclass of DukeException. Thrown when users do not give a valid deadline to the task.
 *
 */
public class InvalidEventException extends DukeException {
    /**
     * Constructor for the exception.
     */
    public InvalidEventException() {
        super("Please enter event in the format [Task name] /at yyyy-mm-dd HH:mm-HH:mm");
    }

    public InvalidEventException(String str) {
        super(str);
    }

}