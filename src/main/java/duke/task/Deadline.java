package duke.task;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * Represents a Task of type Deadline, which has a due time.
 */
public class Deadline extends Task {
    protected LocalDateTime ddlDateTime;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd/MMM/yyyy hh:mm a");

    public Deadline(String deadlineName, LocalDateTime byTime) {
        super(deadlineName);
        this.ddlDateTime = byTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatter.format(ddlDateTime) + ")";
    }
}
