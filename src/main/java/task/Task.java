package task;

import java.time.LocalDate;

public abstract class Task {
    private final String taskName;
    private boolean done;

    public Task(String taskName) {
        this.taskName = taskName;
        done = false;
    }

    /**
     * Sets the task to done.
     */
    public void setDone() {
        done = true;
    }

    /**
     * Returns this Task's list entry string.
     *
     * @return A string representation of this Task's list entry.
     */
    public String listEntry() {
        if (done) {
            return "[X] " + taskName;
        } else {
            return "[ ] " + taskName;
        }
    }

    /**
     * Returns this Task's database entry string.
     *
     * @return A string representation of this Task's database entry.
     */
    public String databaseEntry() {
        if (done) {
            return " | 1 | " + taskName;
        } else {
            return " | 0 | " + taskName;
        }
    }

    /**
     * Checks if the given date takes place during this Task's deadline or period.
     * This method is only relevant to Deadline and Event child classes.
     *
     * @param l The date against which to check this Task.
     * @return true if the task is ongoing or takes place on the given date.
     */
    public abstract boolean isTodayTask(LocalDate l);
}
