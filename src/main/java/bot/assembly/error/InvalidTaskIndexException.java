package bot.assembly.error;

/**
 * An exception that will be thrown when the task list modifying command entered is:
 * 1) not an Integer
 */
public class InvalidTaskIndexException extends Exception {
    /**
     * Constructor
     * @param msg to reply to the user
     */
    public InvalidTaskIndexException(String msg) {
        super(msg);
    }
}
