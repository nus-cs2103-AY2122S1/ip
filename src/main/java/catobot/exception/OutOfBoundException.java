package catobot.exception;

/**
 * Represents exception when value is out of valid range.
 */
public class OutOfBoundException extends BotException {
    /**
     * Constructor for OutOfBoundException.
     *
     * @param expected The expected range of values.
     * @param actual The actual value.
     */
    public OutOfBoundException(String expected, String actual) {
        super(String.format("OOPS!!! The expected value is %s but you give me %s", expected, actual));
    }
}
