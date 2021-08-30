package duke.task;

/**
 * Represents a task
 */
public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Task constructor.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns 'X' character if task is done, ' ' otherwise.
     *
     * @return 'X' character if task is done, ' ' otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /** Sets isDone to true to mark task as done */
    public void setDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}