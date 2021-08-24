package catobot.exception;

public class EmptyTaskListException extends BotException {
    /**
     * Creates a EmptyTaskListException.
     */
    public EmptyTaskListException() {
        super("There is no tasks currently! Try to add one!");
    }

    /**
     * Gets the message of the EmptyTaskListException.
     *
     * @return The error message.
     */
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}