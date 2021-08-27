package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Parent class. Inherited by a bunch of classes like Event and Deadline.
 *
 * @author Ruth Poh
 */
public class Task {
    protected String taskString;
    protected boolean isDone;
    protected LocalDate date;

    /**
     * Constructor to initialize Deadline.
     *
     * @param taskString Task.
     */
    public Task(String taskString) {
        this.taskString = taskString;
        this.isDone = false;
    }

    /**
     * Returns date stored in Task in String form.
     * Is blank because Todo doesn't have a Time. Is Overwritten in Event and Deadline.
     * @return ""
     */
    public String getTime() {
        return "";
    }

    /**
     * Returns date stored in Task in String form.
     * Occurs when wanting to convert date to simplified form for saving in file.
     * @return ""
     */
    public String getTimeStorage() {
        return "";
    }

    /**
     * Returns the descriptive String of task. Used in displayList.
     * @return string of task.
     */
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + taskString;
    }

    /**
     * Returns the pure String of task.
     * @return Pure String of task.
     */
    public String getTaskStr() {
        return taskString;
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
}
