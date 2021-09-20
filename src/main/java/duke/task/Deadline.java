package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A type of task that tracks a deadline (date and time) and the task description.
 */
public class Deadline extends Task {
    protected LocalDate by;
    private final String DATE_FORMAT = "MMM d yyyy";

    /**
     * Constructor
     *
     * @param description details about the Task.
     * @param by date/time the deadline is due, in yyyy-mm-dd format.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the duke.Deadline's string.
     *
     * @return String.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern(DATE_FORMAT)) + ")";
    }

    /**
     * Returns the task type of duke.Deadline.
     *
     * @return character D to represent duke.Deadline task type.
     */
    @Override
    public char getTaskType() {
        return 'D';
    }

    /**
     * Returns the description in the format that will be saved into Hard drive.
     *
     * @return String
     */
    @Override
    public String toSavedFormat() {
        return super.description + "/~/" + this.by;
    }

    public boolean isSameDate(LocalDate date) {
        return by.equals(date);
    }
}
