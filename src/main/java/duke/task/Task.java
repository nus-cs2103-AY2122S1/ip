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
        String doneIcon = "X";
        String notDoneIcon = " ";
        return (isDone ? doneIcon : notDoneIcon); // mark done task with X
    }

    /** Marks task as done. */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Converts the task into text format meant for persisted storage.
     * Returns the formatted string.
     *
     * @return Formatted string of task meant for persisted storage.
     */
    public String convertToText() {
        return String.format("%d | %s", isDone ? 1 : 0, this.description);
    }

    public abstract boolean isSameDateTime(String dateTime);

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
