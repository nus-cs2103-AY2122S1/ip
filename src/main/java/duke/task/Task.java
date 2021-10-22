package duke.task;

/**
 * Encapsulates a task object.
 */
public class Task {
    private final String description;
    private boolean isDone;

    /**
     * Constructor for Task.
     *
     * @param description description of task task.
     */
    public Task(String description) {
        assert !description.equals("") : "Task description cannot be empty";
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Gets string representation of task object.
     *
     * @return String representation of a task.
     */
    @Override
    public String toString() {
        String done = this.isDone ? "X" : " ";
        return "[" + done + "] " + this.description;
    }
}
