package exceptions;

/**
 * Represents an exception due to user input to the chatbot.
 */
public class UserInputException extends Exception {

    /**
     * Constructor for <code>UserInputException</code>
     * @param message message describing the exception that will be displayed to users
     */
    public UserInputException(String message) {
        super(message);
    }
}
