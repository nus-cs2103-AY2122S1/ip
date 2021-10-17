package bob.exception;

/**
 * Represents the exception thrown by Bob when the user tries to mark as completed or remove a task not inside the list.
 */
public class NotFoundException extends BobException {
    /**
     * Constructor for a new NotFoundException instance.
     */
    public NotFoundException() {
        super();
    }

    /**
     * Gets error message for when the user tries to mark as completed or remove a task that is not inside the
     * task list.
     *
     * @return Error message.
     */
    @Override
    public String getMessage() {
        return "Huh what task is that :/\n";
    }
}
