package catobot.exception;

/**
 * OutOfBoundException is thrown when a value is out of valid range.
 */
public class OutOfBoundException extends BotException {
    /**
     * Creates an OutOfBoundException.
     *
     * @param expected The expected range of values.
     * @param actual The actual value.
     */
    public OutOfBoundException(String expected, String actual) {
        super(String.format("OOPS!!! The expected value is %s but you give me %s", expected, actual));
    }

    /**
     * Creates an OutOfBoundException with customized error message.
     *
     * @param errorMessage The customized error message.
     */
    public OutOfBoundException(String errorMessage) {
        super(errorMessage);
    }
}
