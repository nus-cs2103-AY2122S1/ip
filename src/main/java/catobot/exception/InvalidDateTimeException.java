package catobot.exception;

/**
 * Represents exception thrown when the date format is wrong.
 */
public class InvalidDateTimeException extends BotException {
    /**
     * Constructor for InvalidDateTimeException.
     */
    public InvalidDateTimeException() {
        super("I only know kindergarten math, "
                + "so please pass in format like 2021-08-12 1800");
    }

}
