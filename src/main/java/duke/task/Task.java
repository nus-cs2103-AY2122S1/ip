package duke.task;

/**
 * This class encapsulates a duke.task.Task.
 *
 * @author Tan Yi Guan
 * @version CS2103T AY21/22 Semester 1
 */
public abstract class Task {
    private final String description;
    private boolean isDone;

    /**
     * Instantiates a new duke.task.Task.
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

    public String convertToTxt() {
        return String.format("%d | %s", isDone ? 1 : 0, this.description);
    };

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
