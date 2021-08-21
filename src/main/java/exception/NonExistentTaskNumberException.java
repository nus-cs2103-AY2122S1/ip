package exception;

/**
 * Encapsulates the exception thrown when the user inputs a task number that does not exist in the list.
 */
public class NonExistentTaskNumberException extends DukeException {
    /**
     * Constructor to instantiate a `exception.NonExistentTaskNumberException`.
     *
     * @param taskNumber is the invalid input by the user
     */
    public NonExistentTaskNumberException(int taskNumber) {
        super(String.format("Task %d does not exist in the list", taskNumber));
    }
}
