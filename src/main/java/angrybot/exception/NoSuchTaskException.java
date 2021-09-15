package angrybot.exception;

/**
 * Signals that an error has occurred while trying to do
 * an operation related to a task where it couldn't be found.
 *
 * @author Zhi Bin
 * @version AngryBot Level 10
 */
public class NoSuchTaskException extends AngryBotException {

    /**
     * Returns a formatted error message to indicate to the
     * user that the task they want to do some operations on
     * cannot be found.
     *
     * @return The formatted error message.
     */
    @Override
    public String getMessage() {
        return "Hello??? You sure got this task or not???";
    }
}
