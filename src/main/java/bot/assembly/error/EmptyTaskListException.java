package bot.assembly.error;

/**
 * An exception that will be thrown when task list is empty
 */
public class EmptyTaskListException extends Exception {

    /**
     * Constructor
     * @param msg to reply to the user
     */
    public EmptyTaskListException(String msg) {
        super(msg);
    }
}
