/**
 * This class represents exceptions with missing arguments.
 */
public class MissingTaskException extends DukeException {
    /***
     * Constructor to create a Duke exception.
     *
     * @param msg The error message of the exception.
     */
    public MissingTaskException(String msg) {
        super(msg);
    }
}