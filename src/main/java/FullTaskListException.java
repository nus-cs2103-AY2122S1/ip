/**
 * Encapsulates the exception thrown when there are attempts to add to a full task list.
 */
public class FullTaskListException extends Exception {
    /**
     * Constructor to instantiate a `FullTaskListException`.
     *
     * @param capacity is the maximum capacity of the list
     */
    public FullTaskListException(int capacity) {
        super(String.format("Unable to add task to list as it has reached max capacity of %d item(s)", capacity));
    }
}
