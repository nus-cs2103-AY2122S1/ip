package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates the Deadline class which has a date as its deadline.
 */
public class Deadline extends Task {
    private String by;
    private LocalDate date;

    /**
     * Constructor for new deadline objects as input by user.
     *
     * @param description Description of the deadline task.
     * @param by Date of the deadline as input by user.
     */
    public Deadline(String description, String by) {
        super(description);
        this.date = LocalDate.parse(by.trim());
        this.by = by;
    }

    /**
     * Constructor for deadline objects as read from saved file.
     *
     * @param description Description of the deadline task.
     * @param isDone Indicates if task is done.
     * @param by Date of the deadline task.
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.date = LocalDate.parse(by.trim());
        this.by = by;
    }

    /**
     * Gets the task type indicator to be displayed when the
     * user lists tasks.
     *
     * @return The String representation of the type label for Deadline.
     */
    @Override
    public String getTypeIndicator() {
        return "[D]";
    }

    /**
     * Converts the Deadline object to a String representation to be stored
     * in file.
     *
     * @return The String representation of the file record of the Deadline object.
     */
    @Override
    public String toFileRecord() {
        return String.format("E | %d | %s | %s",
                this.isDone ? 1 : 0 , this.description, this.by);
    }

    /**
     * Returns a String representation of the object
     *
     * @return The String representation of the Deadline object.
     */
    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(),
                date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));

    }
}
