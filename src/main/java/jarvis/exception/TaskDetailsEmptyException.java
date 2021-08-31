package jarvis.exception;

/**
 * Encapsulates an exception when the task details are empty.
 */
public class TaskDetailsEmptyException extends JarvisException {
    /**
     * Constructor for TaskDetailsEmptyException.
     *
     * @param emptyField The field that is empty.
     */
    public TaskDetailsEmptyException(String emptyField) {
        super(String.format(
                "Violation of Stark Industries task %s format! It should not be empty!",
                emptyField
        ));
    }
}
