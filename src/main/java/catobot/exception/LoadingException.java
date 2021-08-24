package catobot.exception;

/**
 * Represents exception when the initial loading of files has problem.
 */
public class LoadingException extends BotException {
    /**
     * Constructor for LoadingException.
     */
    public LoadingException() {
        super("Meow! " +
                "This is an error when loading the file! " +
                "You will have a new task list meow >.<");
    }
}