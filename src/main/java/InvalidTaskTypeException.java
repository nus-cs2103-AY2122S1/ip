/**
 * Encapsulates the exception thrown when a user inputs an invalid message that is not a task.
 */
public class InvalidTaskTypeException extends Exception {
    /**
     * Constructor to instantiate an `InvalidTaskTypeException`
     *
     * @param description the invalid input by the user
     */
    public InvalidTaskTypeException(String description) {
        super(String.format(
            "'%s' is not a valid task type. " +
            "Tasks should begin with one of the following: todo, deadline, event",
            description
        ));
    }
}
