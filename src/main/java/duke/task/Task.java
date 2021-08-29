package duke.task;

/**
 * Encapsulates a Task with a String description.
 */
public class Task {
    /** The description of the task */
    private final String description;

    /** The state of whether the task has been done */
    private boolean isDone;

    /**
     * Constructs a Task object.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the checkmark for whether the task is done.
     *
     * @return The whether the task is done.
     */
    private String status() {
        return this.isDone ? "X" : " ";
    }

    /**
     * Marks the task as done.
     */
    public void done() {
        this.isDone = true;
    }

    /**
     * Returns the general task as data for saving.
     *
     * @return Task object save data.
     */
    public String saveData() {
        String doneState = this.isDone ? "1" : "0";

        return doneState + " " + this.description;
    }

    /**
     * Returns the string form of the task object.
     *
     * @return The string form of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] ", this.status()) + description;
    }
}
