package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Implements a Deadline object extending from Task.
 */
public class Deadline extends Task {
    protected final LocalDateTime dateTime;

    /**
     * Constructs a Deadline object.
     * @param description Description of the deadline.
     * @param dateTime Due date and time.
     */
    public Deadline(String description, String dateTime) {
        super(description);
        this.dateTime = convert(dateTime);
    }

    private LocalDateTime convert(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
        return LocalDateTime.parse(dateTime, formatter);
    }

    /**
     * Returns the string representation of the deadline.
     * @return String representation of the deadline.
     */
    @Override
    public String toString() {
        return "[D][" + this.getStatusIcon() + "] " + this.getDescription() + " (by: "
                + this.dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")) + ")";
    }
}
