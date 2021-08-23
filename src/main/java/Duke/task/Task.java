package duke.task;

import java.time.LocalDate;

/**
 * Represents a task.
 */
abstract public class Task {

    private String details;
    private boolean completed;

    public Task(String details) {
        this.details = details;
        this.completed = false;
    }

    /**
     * Completes task.
     */
    public void complete() {
        this.completed = true;
    }

    /**
     * Checks if task is completed.
     *
     * @return True if completed; false otherwise.
     */
    public boolean isCompleted() {
        return this.completed;
    }

    /**
     * Returns task details.
     *
     * @return Task details.
     */
    public String getDetails() {
        return this.details;
    }

    /**
     * Returns date of task.
     *
     * @return Task date.
     */
    abstract public LocalDate getDate();

    /**
     * Returns time of task.
     *
     * @return Task time.
     */
    abstract public int getTime();

    /**
     * Returns a String representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        String status = this.completed ? "[X]" : "[ ]";
        return status + " " + details;
    }
}
