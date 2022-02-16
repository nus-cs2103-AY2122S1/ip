package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deadline class which is subclass of Task.
 */
public class Deadline extends Task {

    // Time of deadline
    protected String by;

    /**
     * Constructor for the deadline class.
     *
     * @param description the description of the deadline task.
     * @param by the date of the deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        try {
            LocalDate date = LocalDate.parse(by);
            this.by = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException e) {
            this.by = by;
        }
    }

    /**
     * Returns the string representation of the deadline task.
     *
     * @return string representation of deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns the string representation which is use to store the tasks.
     *
     * @return string representation used for storing task.
     */
    @Override
    public String toDataFormat() {
        return String.format("D | %s | %s | %s | %s", isDone ? "1" : "0",
                description, getPlacesRepresentation(), by);
    }
}
