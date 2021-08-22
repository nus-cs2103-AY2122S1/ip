package duke.task;

import java.time.LocalDate;

/**
 * Abstract class providing the skeleton of all Tasks.
 */
public abstract class Task {
    private boolean done = false;
    private String description;

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
     * @param done Whether the task is done.
     * @param description Description of the task.
     */
    public Task(boolean done, String description) {
        this.description = description;
        this.done = done;
    }

    /**
     * Marks the task as done.
     */
    public void setDone() {
        done = true;
    }

    /**
     * Returns a string representation of the task for display.
     *
     * @return String representation of the task for display.
     */
    @Override
    public String toString() {
        char indicator = ' ';
        if (done) {
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
        if (done) {
            indicator = '1';
        }
        return indicator + "|" + this.description;
    }
}