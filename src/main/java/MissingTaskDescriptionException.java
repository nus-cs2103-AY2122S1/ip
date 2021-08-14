/**
 * Encapsulates the exception thrown when a user inputs a valid task with an empty description.
 */
public class MissingTaskDescriptionException extends Exception {
    /**
     * Constructor to instantiate a `MissingTaskDescriptionException`
     *
     * @param taskType is the type of the task
     */
    public MissingTaskDescriptionException(String taskType) {
        super(String.format(
           "The description of a %s task cannot be empty",
           taskType
        ));
    }
}
