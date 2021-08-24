package catobot.exception;

/**
 *  * Represents exception when creating event with invalid input.
 */
public class InvalidEventException extends BotException {
    /**
     * Constructor for InvalidEventException.
     */
    public InvalidEventException() {
        super("Oh no, please give me a valid event with date >.<");
    }

}