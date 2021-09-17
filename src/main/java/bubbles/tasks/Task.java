package bubbles.tasks;

import java.time.Clock;
import java.time.LocalDate;

/**
 * A class that represents a Task object - a piece of
 * work to be done or undertaken.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * A public constructor for a Task Object, initializing the
     * field description (of the task), and whether the task has
     * been completed yet.
     *
     * @param description The description of the task.
     * @param isDone Whether the task has been completed.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void setAsDone() {
        this.isDone = true;
    }

    /**
     * Retrieves the status of the task, whether is has been
     * completed.
     * @return "X" if completed, " " if not yet.
     */
    public String getStatus() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the String representation of the Task.
     *
     * @return The String representation of the task.
     */
    public String format() {
        return this.toString();
    }

    /**
     * Formats the date input from the user (in the form of yyyy-mm-dd),
     * and store the date in the form of a LocalDate object.
     *
     * @param dateInput Date input form the user, formatted in yyyy-mm-dd.
     * @return LocalDate Object representing the input date.
     */
    public static LocalDate formatDate(String dateInput) {
        // assume that the acceptable format for dates related to deadline and event are in the form of
        // "year-month-day"
        LocalDate ld = LocalDate.parse(dateInput);

        return ld;
    }

    /**
     * Gets the current date and returns that to the user.
     *
     * @return The current date, eg. 2021-09-09
     */
    public LocalDate getDate() {
        Clock cl = Clock.systemUTC();

        return LocalDate.now(cl);
    }

    /**
     * Returns the String representation of the Task.
     *
     * @return The String representation of the Task.
     */
    @Override
    public String toString() {
        String res = "[" + getStatus() + "] " + description;

        return res;
    }
}
