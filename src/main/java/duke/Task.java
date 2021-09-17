package duke;

/**
 * Abstract class for generic tasks with description.
 */
public abstract class Task {

    protected static final String NOT_DONE_STRING = "0";
    protected static final int DONE = 1;
    protected static final int NOT_DONE = 0;

    protected String description;
    protected boolean isDone;

    /**
     * Initialises Task object with a description and default undone status.
     *
     * @param description describes the nature of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns the appropriate string based on whether the task is done.
     *
     * @return "[X]" if task is done or "[ ]" if not done yet.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Returns the string format in which to store the task to the disk.
     *
     * @return new string format in which to store the task to the disk which is different from toString.
     */
    public abstract String getFileString();

    /**
     * Returns a string in the form of "(done status) (description)" when task is printed.
     *
     * @return string that is displayed when task is printed.
     */
    @Override
    public String toString() {
        return String.format("%s %s", getStatusIcon(), this.description);
    }
}

