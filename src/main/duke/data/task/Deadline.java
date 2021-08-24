package duke.data.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected final LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the data representation of a deadline object.
     *
     * @return The formatted String.
     */
    @Override
    public String toData() {
        return "D|" + super.toData() + "|" + by.format(DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm"));
    }

    /**
     * Returns the String representation of a deadline object.
     *
     * @return THe String representation of a deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}
