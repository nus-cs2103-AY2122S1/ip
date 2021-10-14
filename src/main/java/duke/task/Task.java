package duke.task;

/**
 * Keeps track of name and status of task
 */
public abstract class Task {
    protected String name;
    protected boolean isDone;

    /**
     * Constructs Task object
     *
     * @param name name of task
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Constructs Task object
     *
     * @param name name of task
     * @param isDone status of task
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Returns "X" icon if task is completed
     *
     * @return icon representing status of task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Changes status of task to completed
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Stores task information in a specific format
     *
     * @return task information
     */
    public abstract String toStringInStorage();

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.name);
    }
}
