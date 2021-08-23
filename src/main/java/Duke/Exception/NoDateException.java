package Duke.Exception;

/**
 * Duke.Exception.NoDateException is thrown if there is no date/timing provided by either Duke.Task.Event or Duke.Task.Deadline task.
 */
public class NoDateException extends Exception{
    private final static String SAD_ROBOT_ICON = "[~T-T~]";

    /**
     * Constructs error message for Duke.Exception.NoDateException depending on the eventType.
     * @param taskType it can be either Duke.Task.Deadline or Duke.Task.Event task type.
     */
    public NoDateException(String taskType) {
        super(SAD_ROBOT_ICON +": OOPS!!! The date/timing of a " + taskType + " cannot be empty.");
    }
}
