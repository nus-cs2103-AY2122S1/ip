package duke;

/**
 * A class to represent an EmptyTask.
 */
public class EmptyTask extends Task {

    public static final EmptyTask EMPTY_TASK = new EmptyTask();

    /**
     * Constructor for an EmptyTask object.
     */
    private EmptyTask() {
        super("");
    }


    /**
     * Returns the task information
     *
     * @return the task information
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Checks if the Task is an EmptyTask.
     *
     * @return true if this Task is an EmptyTask, false otherwise
     */
    @Override
    public boolean isEmptyTask() {
        return true;
    }
}
