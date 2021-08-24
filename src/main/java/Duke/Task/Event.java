package Duke.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class represents a event - a task that starts at a specific time
 * and ends at a specific time.
 */
public class Event extends Task {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy kkmm");
    private LocalDateTime date;

    public Event(String description, LocalDateTime date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.date.format(FORMATTER));
    }
}


