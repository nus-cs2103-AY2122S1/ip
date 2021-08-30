package duke.exceptions;

/**
 * Class of exception that handles marking task
 * with a wrong format of time
 */
public class WrongTimeFormatException extends DukeException {

    /**
     * Instantiates a exception that is wrong in its time format
     */
    public WrongTimeFormatException() {
        super("Time format needs to be of the form HH:mm!");
    }
}
