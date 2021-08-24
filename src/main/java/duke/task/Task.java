package duke.task;

/**
 * Encapsulates a task object.
 *
 * @author Dickson
 */
public class Task {
    private final String description;
    private boolean isDone;

    /**
     * Constructor for Task object.
     *
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    /**
     *
     * @return String representation of a task.
     */
    @Override
    public String toString() {
        String done = this.isDone ? "X" : " ";
        return "[" + done + "] " + this.description;
    }
}
