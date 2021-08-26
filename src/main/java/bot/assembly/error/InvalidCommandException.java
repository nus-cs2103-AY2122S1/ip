package bot.assembly.error;

/**
 * An exception that will be thrown when an invalid command is entered
 */
public class InvalidCommandException extends Exception {

    /**
     * Constructor
     * @param msg to reply to the user
     */
    public InvalidCommandException(String msg) {
        super(msg);
    }
}
