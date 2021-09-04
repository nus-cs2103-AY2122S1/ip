package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class is a child class of Task.
 * Contains additional "by" parameter that strictly requires input to be of LocalDate type.
 * @author  Hoon Darren
 * @version 1.0
 * @since   2021-08-21
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructor to create a new Deadline object.
     *
     * @param description the description of the Deadline Task.
     * @param by due date of Deadline Task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Retrieves the parameter by as a string.
     *
     * @return by property as a String.
     */
    public String getBy() {
        return this.by.toString();
    }

    /**
     * Retrieves a string describing the Deadline's status.
     *
     * @return string describing the Deadline's status.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String deadline = by.format(formatter);
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
