package duke.tasks;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Parent Task class. Inherited by Event, Deadline, Todo.
 * @author Ruth Poh
 */
public abstract class Task {
    protected String taskString;
    protected boolean isDone;
    protected LocalDate date;
    protected LocalTime time;

    /**
     * Initialises Task.
     * @param taskString Task.
     */
    public Task(String taskString) {
        this.taskString = taskString;
        this.isDone = false;
    }

    /**
     * Getter method for taskString.
     * @return String of task.
     */
    public String getTaskString() {
        return taskString;
    }

    public abstract String getDate();

    public abstract String getTime();

    /**
     * Checks if Task has a time.
     * @return True if task has a time, false if not.
     */
    public boolean hasTime() {
        return (this.time != null);
    }

    /**
     * Returns descriptive String of task.
     * @return String of task.
     */
    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + taskString;
    }

    /**
     * Returns string 'X' if task is done, ' ' if task is not.
     * @return string 'X' or 'x'.
     */
    public String getTaskStatus() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks task as done.
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

    /**
     * Returns date stored in Task in String form.
     * Occurs when wanting to convert date to simplified form for saving in file.
     * @return ""
     */
    public String getDateTimeStorage() {
        return "";
    }

    /**
     * Returns String for storage.
     * @return String for storage.
     */
    public String toStorageString() {
        return "";
    }


}
