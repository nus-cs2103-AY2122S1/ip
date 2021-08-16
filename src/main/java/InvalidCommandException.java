/**
 * InvalidCommandException is an exception that is thrown when command is not recognized for any type of task.
 */
public class InvalidCommandException extends Exception {
    private final static String SAD_ROBOT_ICON = "[~T-T~]";

    /**
     * Constructs error message for InvalidCommandException.
     */
    public InvalidCommandException() {
        super(SAD_ROBOT_ICON +": OOPS!!! I'm sorry, but I don't know what that means.");
    }
}
