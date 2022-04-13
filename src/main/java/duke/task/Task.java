package duke.task;

/**
 * Abstract class providing the skeleton of all Tasks.
 */
public abstract class Task {
    private boolean isDone = false;
    private final String description;
    /**
     * Class constructor.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Alternative constructor for storage to load tasks already done.
     *
     * @param isDone Whether the task is done.
     * @param description Description of the task.
     */
    public Task(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Marks the task as done.
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Returns a string representation of the task for display.
     *
     * @return String representation of the task for display.
     */
    @Override
    public String toString() {
        char indicator = ' ';
        if (isDone) {
            indicator = 'X';
        }
        return "[" + indicator + "] " + this.description;
    }

    /**
     * Returns a string representation of the task to be saved as.
     *
     * @return String representation of the task to be saved as.
     */
    public String saveString() {
        char indicator = '0';
        if (isDone) {
            indicator = '1';
        }
        return indicator + "|" + this.description;
    }

    public String getDescription() {
        return this.description;
    }
}
