package catobot.exception;

/**
 * Represents exception thrown when the date format is wrong.
 */
public class InvalidDateTimeException extends BotException {
    /**
     * Creates a InvalidDateTimeException.
     */
    public InvalidDateTimeException() {
        super("Meow! " +
                "I only know kindergarten math, " +
                "so please pass in format like 2021-08-12 1800");
    }

}
