package bot.assembly.error;

/**
 * An exception that will be thrown when the task list modifying command entered is:
 * 1) having the respective index is out of the range of the array
 */
public class TaskOutOfRangeException extends Exception {

    /**
     * Constructor
     * @param msg to reply to the user
     */
    public TaskOutOfRangeException(String msg) {
        super(msg);
    }
}
