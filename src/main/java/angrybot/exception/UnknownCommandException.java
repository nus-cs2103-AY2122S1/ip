package angrybot.exception;

/**
 * Signals that an error has occurred when processing user input.
 * The user input could not be understood by the program or the
 * operation is not supported by the program.
 *
 * @author Zhi Bin
 * @version AngryBot Level 10
 */
public class UnknownCommandException extends AngryBotException {
    public UnknownCommandException() {
        super();
    }

    /**
     * Returns a formatted error message to signal user
     * that the input could not be read or the operation
     * they require is not supported by the program.
     *
     * @return The formatted error message.
     */
    @Override
    public String getMessage() {
        return "I cannot understand what you want lah!!!";
    }
}
