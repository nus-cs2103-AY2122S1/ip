package task;

import java.time.LocalDate;

/**
 * Task abstract class
 */
public abstract class Task {
    protected final String description;
    protected boolean isDone;

    /**
     * Constructor
     *
     * @param input Description of task
     * @param isDone Whether the task is complete
     */
    public Task(String input, boolean isDone) {
        description = input;
        this.isDone = isDone;
    }

    /**Toggles completion of Task
     *
     * @return New status of Task
     */
    public boolean toggleDone() {
        isDone = !isDone;
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    /**
     * String representation of Task
     *
     * @return task display
     */
    @Override
    public String toString() {
        String checkBox = isDone
                ? "[X] "
                : "[ ] ";
        return checkBox + description;
    }

    /**
     * The string representation of Task to be used for saving
     *
     * @return Save string
     */
    public abstract String saveString();

    /**
     * Checks if the task falls on a given date (if applicable)
     *
     * @param date Date to check
     * @return Whether task is tagged to the passed date
     */
    public abstract boolean isDate(LocalDate date);
}
