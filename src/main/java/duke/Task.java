package duke;

/**
 * Abstract class for generic tasks with description.
 */
public abstract class Task {

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

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone? "[X]" : "[ ]");
    }

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
