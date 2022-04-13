package angrybot.exception;

/**
 * Signals that an error has occurred due to empty description of a task.
 *
 * @author Zhi Bin
 * @version AngryBot Level 10
 */
public class NoTaskDescriptionException extends AngryBotException {
    public NoTaskDescriptionException() {
        super();
    }

    /**
     * Returns a formatted error message to tell the
     * user that a task must come with a description.
     *
     * @return The formatted error message.
     */
    @Override
    public String getMessage() {
        return "Hello??? Your task no description one ah???";
    }
}
