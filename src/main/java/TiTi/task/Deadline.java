package titi.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents the Deadline task.
 * Contains String description of the task.
 * Contains String description of the date of task.
 * Contains LocalDate object of the date of task (if applicable).
 * Contains boolean value of whether the task has been completed.
 */
public class Deadline extends Task {

    protected String by;
    protected LocalDate date;

    /**
     * Initialises a Deadline instance.
     *
     * @param description description of the task
     * @param by string description of the time to complete the task by
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        checkDate(by);
    }

    /**
     * Checks if the string input can be converted to a LocalDate object.
     */
    private void checkDate(String by) {
        if (by.matches("\\d{4}-\\d{2}-\\d{2}")) {
            date = LocalDate.parse(by);
            this.by = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
    }

    /**
     * Returns string representation of the task.
     *
     * @return string representation of task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
