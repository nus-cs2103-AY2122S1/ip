package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task, which has a description and a date by which the
 * task should be completed.
 */
public class Deadline extends Task {
    protected String by;
    protected LocalDate byDate;

    /**
     * Class constructor that takes a description and a string which describes
     * the deadline of the task. If the string is given in the form YYYY-MM-DD,
     * a LocalDate object will be created to represent the date.
     *
     * @param description Description of the deadline task
     * @param by Deadline of the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        try {
            byDate = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            byDate = null;
        }
    }

    /**
     * Returns the LocalDate object which represents the deadline of the task
     *
     * @return Deadline of the task
     */
    public LocalDate getByDate() {
        return byDate;
    }

    /**
     * Returns a formatted String representing the deadline of the task in the form
     * MMM d YYYY.
     *
     * @return Formatted date string
     */
    public String parseByDate() {
        return getByDate().format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Returns the string representation of the deadline, including whether it
     * has been completed and when it is due.
     *
     * @return String representation of deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + ((byDate == null) ? by : parseByDate()) + ")";
    }
}