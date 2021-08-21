package exception;

/**
 * Encapsulates an exception when the user inputs a task number that does not exist in the list.
 */
public class NonExistentTaskNumberException extends DukeException {
    public NonExistentTaskNumberException(int taskNumber) {
        super(String.format("Task %d does not exist in the list", taskNumber));
    }
}
