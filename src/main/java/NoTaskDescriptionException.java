/**
 * NoTaskDescriptionException is thrown when there is no task description for any task.
 */
public class NoTaskDescriptionException extends Exception{
    private final static String SAD_ROBOT_ICON = "[~T-T~]";

    /**
     * Constructs error message for NoTaskDescriptionException depending on taskType.
     * @param taskType type of Task
     */
    public NoTaskDescriptionException(String taskType) {
        super(SAD_ROBOT_ICON +": OOPS!!! The description of a " + taskType + " cannot be empty.");
    }

}
