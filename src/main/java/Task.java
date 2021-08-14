/**
 * This class encapsulates a Task.
 *
 * @author Tan Yi Guan
 * @version CS2103T AY21/22 Semester 1
 */
public class Task {
    private final String description;
    private boolean isDone;

    /**
     * Instantiates a new Task.
     *
     * @param description the description for the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the representation of whether a task is done.
     *
     * @return Character that represents whether a task is done.
     */
    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /** Marks task as done. */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * String representation of a task.
     *
     * @return String representation of a task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
