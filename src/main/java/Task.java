import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Parent class. Inherited by a bunch of classes like Event and Deadline.
 *
 * @author Ruth Poh (Lab 10H)
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

    public String getTime() {
        return "";
    }

    /**
     * Returns string of task.
     *
     * @return string of task.
     */
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + taskString;
    }

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
