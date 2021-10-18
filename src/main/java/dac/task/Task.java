package dac.task;

import java.time.LocalDateTime;

/**
 * Represents a task.
 */
public abstract class Task implements Comparable<Task> {

    public static final String COMPLETED_SYMBOL = "X";
    private final char label;
    private final String details;
    private boolean isCompleted;

    /**
     * Constructor.
     *
     * @param label Task label.
     * @param details Details of the task.
     */
    public Task(char label, String details) {
        this.label = label;
        this.details = details;
        isCompleted = false;
    }

    /**
     * Completes task.
     */
    public void complete() {
        isCompleted = true;
    }

    /**
     * Checks if task is completed.
     *
     * @return True if completed; false otherwise.
     */
    public boolean isTaskCompleted() {
        return isCompleted;
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
    public abstract LocalDateTime getDate();

    /**
     * Returns task label.
     *
     * @return Task label.
     */
    public char getLabel() {
        return label;
    }
    /**
     * Returns a String representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        String status = isCompleted
                ? "[" + COMPLETED_SYMBOL + "]"
                : "[ ]";
        return status
                + " "
                + details;
    }

    /**
     * Compares this task to another task by date.
     * A task without a date is smaller than a task with a date.
     * A completed task is smaller than an incomplete task.
     *
     * @param task The other task to be compared to.
     * @return -1 if this task occurs at an earlier date;
     * 0 if the tasks occur on the same date;
     * 1 if this task occurs at a later date.
     */
    @Override
    public int compareTo(Task task) {
        if (!(this.isTaskCompleted() && task.isTaskCompleted())) {
            if (this.isTaskCompleted()) {
                return -1;
            } else if (task.isTaskCompleted()) {
                return 1;
            }
        }
        LocalDateTime myDate = this.getDate();
        LocalDateTime yourDate = task.getDate();
        if (myDate == null && yourDate == null) {
            return 0;
        } else if (myDate == null) {
            return -1;
        } else if (yourDate == null) {
            return 1;
        }
        return myDate.compareTo(yourDate);
    }
}
