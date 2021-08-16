/**
 * EmptyDescException class. Subclass of PetalException.
 * Thrown when user does not give a description for Event/Deadline.
 * Example: "deadline /by 8pm"
 */
public class EmptyDescException extends PetalException {

    //Exception message
    private String message;
    //Initialized if used as wrapper
    private Throwable cause;

    /**
     * Constructor for the EmptyDescException
     * @param message The exception message
     */
    public EmptyDescException(String message) {
        super(message);
        this.message = message;
    }

    /**
     * Constructor for the EmptyDescException (Used as wrapper)
     * @param message The exception message
     * @param cause Initial Cause
     */
    public EmptyDescException(String message, Throwable cause) {
        super(message);
        this.message = message;
        this.cause = cause;
    }

}
