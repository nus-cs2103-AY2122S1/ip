package exceptions;

/**
 * Represents an exception due to the user using an invalid
 * word.
 */
public class InvalidInputException extends UserInputException {
    /**
     * Constructor for <code>InvalidInputException</code>
     *
     * @param message message describing the exception that will be displayed to users
     */
    public InvalidInputException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means\n*sad quack*");
    }
}
