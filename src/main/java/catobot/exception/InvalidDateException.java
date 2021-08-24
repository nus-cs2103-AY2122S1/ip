package catobot.exception;

/**
 * Represents exception thrown when the date format is wrong.
 */
public class InvalidDateException extends BotException {
    /**
     * Creates a InvalidDateException.
     */
    public InvalidDateException() {
        super("Meow! " +
                "I only know kindergarten math, " +
                "so please pass in yyyy-mm-dd format!");
    }

}
