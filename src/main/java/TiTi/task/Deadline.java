package TiTi.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents the Deadline task.
 * Contain String description of the task.
 * Contain String description of the date of task.
 * Contain LocalDate object of the date of task (if applicable).
 * Contain boolean value of whether the task has been completed.
 */
public class Deadline extends Task {

    protected String by;
    protected LocalDate date;

    /**
     * Constructor for Deadline class.
     *
     * @param description description of the task.
     * @param by string description of the time to complete the task by.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        checkDate(by);
    }

    private void checkDate(String by) {
        if (by.matches("\\d{4}-\\d{2}-\\d{2}")) {
            date = LocalDate.parse(by);
            this.by = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}