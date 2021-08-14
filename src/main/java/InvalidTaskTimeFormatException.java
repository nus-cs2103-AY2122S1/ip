/**
 * Encapsulates the exception thrown when a task meant to carry time information is incorrectly formatted.
 */
public class InvalidTaskTimeFormatException extends Exception {
    /**
     * Constructor to instantiate an `InvalidTaskTimeFormatException`.
     *
     * @param taskType is the type of the task
     * @param timeSplitter separates the action from the time information in the task description
     */
    public InvalidTaskTimeFormatException(String taskType, String timeSplitter) {
        super(String.format(
            "Please enter a valid %s task in the form of X %s Y, where X is an action and Y is a time",
            taskType,
            timeSplitter
        ));
    }
}
