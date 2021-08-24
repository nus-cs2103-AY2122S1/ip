package mango.task;

import mango.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that is a deadline. A <code>Deadline</code> object corresponds to a
 * <code>Task</code> that has a description, a completion status, and a date by which the task should be completed.
 */
public class Deadline extends Task {
    protected LocalDate date;

    /**
     * Constructor for a Deadline.
     *
     * @param description The description of the deadline.
     * @param date The date on which the deadline falls.
     */
    public Deadline(String description, String date) {
        super(description);
        this.date = LocalDate.parse(date);
    }

    /**
     * Constructor for a Deadline, initialised with a completion status.
     *
     * @param description The description of the deadline.
     * @param date The date on which the deadline falls.
     * @param status The completion status of the deadline.
     */
    public Deadline(String description, String date, String status) {
        super(description, status.equals("true"));
        this.date = LocalDate.parse(date);
    }

    /**
     * Returns the string representation of the type of task.
     *
     * @return The string representation of the type of task.
     */
    public String getType() {
        return "D";
    }

    /**
     * Checks if the input string array is valid.
     *
     * @param arr The input string array.
     * @return True if the string array is valid, else false.
     * @throws DukeException If the array length is 1.
     */
    public static boolean isValid(String[] arr) throws DukeException {
        if (arr.length == 1) {
            throw new DukeException(" ☹ OOPS!!! The description of a deadline cannot be empty.");
        }

        return true;
    }

    /**
     * Returns the description of the deadline.
     *
     * @return The description of the deadline.
     */
    public String getDescription() {
        return String.format("%s (by: %s)", this.description, this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    /**
     * Returns the string representation of the deadline.
     *
     * @return The string representation of the deadline.
     */
    public String save() {
        return String.format("%s:%s:%s:%s\n", this.getType(), this.getStatus(), this.description, this.date);
    }
}
