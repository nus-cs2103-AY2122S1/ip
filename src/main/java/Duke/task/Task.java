package duke.task;

import java.time.LocalDateTime;

/**
 * Represents a task.
 */
abstract public class Task {

    private String details;
    private boolean completed;

    public Task(String details) {
        this.details = details;
        completed = false;
    }

    /**
     * Completes task.
     */
    public void complete() {
        completed = true;
    }

    /**
     * Checks if task is completed.
     *
     * @return True if completed; false otherwise.
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Returns task details.
     *
     * @return Task details.
     */
    public String getDetails() {
        return details;
    }

    /**
     * Returns date of task.
     *
     * @return Task date.
     */
    abstract public LocalDateTime getDate();

    /**
     * Returns a String representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        String status = completed
                ? "[X]"
                : "[ ]";
        return status
                + " "
                + details;
    }
}
