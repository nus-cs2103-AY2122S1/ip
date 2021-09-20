package duke;

/**
 * Abstract class inherited by the ToDo, DeadLine and Event classes.
 */
abstract public class Task implements Comparable<Task>{
    private final String description;
    protected boolean isDone;
    protected char type;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.type = ' ';
    }

    public String getDescription() {
        return this.description;
    }

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

    public TaskType getType() {
        return null;
    }

    @Override
    abstract public int compareTo(Task other);


}
