package bot.assembly.error;

/**
 * An exception that will be thrown when the command's format is invalid
 */
public class InvalidCommandFormatException extends Exception {

    /**
     * Constructor
     * @param msg to reply to the user
     */
    public InvalidCommandFormatException(String msg) {
        super(msg);
    }
}
