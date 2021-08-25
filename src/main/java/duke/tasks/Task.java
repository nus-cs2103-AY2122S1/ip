package duke.tasks;

/**
 * Represents a task.
 */
public class Task {

    /**
     * Description of task.
     */
    private final String description;

    /**
     * Whether the task has been completed.
     */
    private boolean isDone;

    /**
     * Constructor for Task.
     *
     * @param description description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
