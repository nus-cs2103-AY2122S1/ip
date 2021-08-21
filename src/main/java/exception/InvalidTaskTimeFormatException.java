package exception;

/**
 * Encapsulates an exception when a task meant to carry time information is incorrectly formatted.
 */
public class InvalidTaskTimeFormatException extends DukeException {
    public InvalidTaskTimeFormatException(String taskType, String timeSplitter) {
        super(String.format(
            "Please enter a valid %s task in the form of X %s Y, where X is an action and Y is a time",
            taskType,
            timeSplitter
        ));
    }
}
