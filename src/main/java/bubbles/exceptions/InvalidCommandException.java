package bubbles.exceptions;

/**
 * A class that represents the exception when Bubbles receives
 * an invalid command/command the bot does not understand.
 */
public class InvalidCommandException extends Exception {
    /**
     * A public constructor for the InvalidCommandException.
     * @param message The message containing details of the InvalidCommandException.
     */
    public InvalidCommandException(String message) {
        super(message);
    }

    /**
     * Returns the String representation of the Exception.
     *
     * @return The String representation of the Exception.
     */
    @Override
    public String toString() {
        return "☹ OOPS!!! I'm sorry, but I do not know what this means. ☹";
    }
}
