/**
 * EmptyDescException class. Subclass of PetalException.
 * Thrown when user does not give a description for Event/Deadline.
 * Example: "deadline /by 8pm"
 */
public class EmptyDescException extends PetalException {

    //Exception message
    private String message;

    /**
     * Constructor for the EmptyDescException
     * @param message The exception message
     */
    public EmptyDescException(String message) {
        super(message);
        this.message = message;
    }
}
