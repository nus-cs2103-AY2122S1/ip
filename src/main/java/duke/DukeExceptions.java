package duke;

/**
 * An exception class for Duke program
 **/
public class DukeExceptions extends Exception {
    /**
     * Create a new DukeException with the specified string as the message
     *
     * @param msg The message which the exception should contain.
     **/
    public DukeExceptions(String msg) {
        super(msg);
    }
}
