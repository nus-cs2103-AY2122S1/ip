package duke.data;

/**
 * Encapsulates an exception meant for Duke.
 * This class is extended from the Exception class,
 * and has an additional attribute of a header
 * that is used when printing error messages.
 *
 * @author Jason Ng
 * @version Duke Level-10
 */
public class DukeException extends Exception {
    /** Header for the exception message */
    protected String header;

    /**
     * Constructor for a DukeException.
     *
     * @param message The message for the error to be constructed with.
     */
    public DukeException(String message) {
        super(message);
        this.header = "☹ OOPS!!! ";
    }

    /**
     * Returns the error message of the error.
     * Overrides getMessage() of the Exception class,
     * so that the messages have the header we want
     * for DukeExceptions.
     *
     * @return Error message for DukeExceptions.
     */
    @Override
    public String getMessage() {
        return this.header + super.getMessage();
    }
}
