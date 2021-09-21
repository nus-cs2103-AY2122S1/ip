package duke;

/**
 * Abstract class inherited by the ToDo, DeadLine and Event classes.
 */
abstract public class Task implements Comparable<Task>{
    private final String description;
    protected boolean isDone;
    protected char type;

    /**
     * Public constructor meant to be used by called by child classes to set
     * the description field.
     *
     * @param description String containing the description of this task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.type = ' ';
    }

    /**
     * Getter method for the description of this Task.
     *
     * @return The description field of this Task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the string representation of this Task.
     * Carries information about the type of this Task, and whether it has been
     * marked as done, along with the description.
     *
     * @return String representation of this Task.
     */
    @Override
    public String toString() {
        return "[" + this.type + "]" + (this.isDone ? "[x] " : "[ ] ") + this.description;
    }

    /**
     * Marks this task as done if not already so. Return value currently unused, was intended to
     * be used for the purposes of tracking if this task was previously marked as done, and this
     * will be implemented via exception tracking in the future.
     *
     * @return False if this task was previously marked as done, true otherwise.
     */
    public boolean markDone() {
        assert !isDone;
        if (this.isDone) {
            return false;
        } else {
            this.isDone = true;
            return true;
        }
    }

    /**
     * Returns the type of this Task.
     * Concrete implementation is done by child classes ToDo, Event and DeadLine.
     *
     * @return The type of this Task.
     */
    abstract public TaskType getType();

    @Override
    abstract public int compareTo(Task other);


}
