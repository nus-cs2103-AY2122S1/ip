package duke.tasks;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Encloses a Task with a date and time that can be marked as done.
 * @author Ruth Poh
 */
public abstract class Task {
    protected String taskString;
    protected boolean isDone;
    protected LocalDate date;
    protected LocalTime time;

    /**
     * Initialises Task.
     * @param taskString String representation of the task
     */
    public Task(String taskString) {
        this.taskString = taskString;
        this.isDone = false;
    }

    /**
     * Getter method for taskString.
     * @return String representation of task.
     */
    public String getTaskString() {
        return taskString;
    }

    /**
     * Returns binary format for isDone.
     * @return 1 is isDone, 0 is !isDone
     */
    public String getIsDoneBinary() {
        if (isDone) {
            return "1";
        } else {
            return "0";
        }
    }

    public abstract String getDate();

    /**
     * Checks if Task has a time.
     * @return True if task has a time, false if not.
     */
    public boolean hasTime() {
        return (this.time != null);
    }

    /**
     * Returns String representation of the Task (with description).
     * @return In form '[X] taskString'.
     */
    @Override
    public String toString() {
        return "[" + this.getTaskStatus() + "] " + taskString;
    }

    /**
     * Returns string 'X' if Task is done, ' ' if task is not.
     * @return string 'X' or 'x'.
     */
    public String getTaskStatus() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks Task as done.
     * @return True if task is marked as done, False if task is already done.
     */
    public boolean markAsDone() {
        if (isDone) {
            return false;
        } else {
            isDone = true;
            return true;
        }
    }

    public abstract String getDateTimeStorage();

    public abstract String toStorageString();

}
