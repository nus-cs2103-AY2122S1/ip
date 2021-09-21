package janet;

/**
 * Represents a to-do task, which is a task that has a description.
 */
public class ToDo extends Task {

    /**
     * Class constructor which takes in the description of the to-do.
     *
     * @param description Description of the to-do task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the to-do, including whether it
     * has been completed.
     *
     * @return String representation of to-do task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
