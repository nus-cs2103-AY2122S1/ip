package duke.exception;

/** Exception to be thrown when there is an invalid task type from the data file */
public class InvalidTaskTypeException extends DukeException {

    /**
     * Constructor for an InvalidTaskTypeException
     * @param task The String of the task that has an invalid task type
     */
    public InvalidTaskTypeException(String task) {
        super(String.format("Incorrect task type for the following task in the file:\n %s", task));
    }
}
