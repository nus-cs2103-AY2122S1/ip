package duke.exception;

/**
 * NoTaskDescriptionException is thrown when there is no task description when finding.
 */
public class InvalidFindException extends Exception{
    private final static String SAD_ROBOT_ICON = "[~T-T~]";

    /**
     * Constructs an error message if there is no task description when searching.
     */
    public InvalidFindException() {
        super(SAD_ROBOT_ICON +": OOPS!!! The description of the task cannot be empty when finding.");
    }
}