package Duke.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class represents a deadline - a task that needs to be
 * done before a specific date/time.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
    private LocalDateTime date;

    public Deadline(String description, LocalDateTime date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.date.format(FORMATTER));
    }
}
