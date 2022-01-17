package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task class
     * initializing a Task instance with given task description.
     *
     * @param description Task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markDone() {
        this.isDone = true;
    }

    /**
     * Return the task info saved in a hard disk.
     *
     * @return Task info saved in a hard disk.
     */
    public String formatForFile() {
        return "| "
                + (this.isDone ? 1 : 0)
                + " | "
                + this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
