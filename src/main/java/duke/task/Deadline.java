package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A task that the user wants to complete by a certain point in time.
 */
public class Deadline extends Task {
    protected boolean hasTime;
    protected LocalDateTime dateTime;
    protected LocalDate date;

    protected DateTimeFormatter formatInput;
    protected DateTimeFormatter formatOutputTime = DateTimeFormatter.ofPattern("MMM d yyyy hh:m a");
    protected DateTimeFormatter formatOutputNoTime = DateTimeFormatter.ofPattern("MMM d yyyy");

    public Deadline(String description, String dateString, DateTimeFormatter formatInput, boolean hasTime) {
        super(description);
        this.formatInput = formatInput;
        this.hasTime = hasTime;
        if (!hasTime) {
            this.date = LocalDate.parse(dateString, formatInput);
        } else {
            this.dateTime = LocalDateTime.parse(dateString, formatInput);
        }
    }

    /**
     * Provides a String representation of the duke.task.Deadline.
     *
     * @return A String representation of the duke.task.Deadline.
     */
    @Override
    public String toString() {
        if (this.hasTime) {
            return "[D][" + this.getStatusIcon() + "] " + this.description + " (by: " +
                    formatOutputTime.format(this.dateTime) + ")";
        } else {
            return "[D][" + this.getStatusIcon() + "] " + this.description + " (by: " +
                    formatOutputNoTime.format(this.date) + ")";
        }
    }
}
