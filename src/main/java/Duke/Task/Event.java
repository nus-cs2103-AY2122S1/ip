package Duke.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class represents a event - a task that starts at a specific date
 * and ends at a specific date.
 */
public class Event extends Task {
    public static final String IDENTIFIER = "E";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy kkmm");
    private LocalDateTime date;

    public Event(String description, LocalDateTime date) {
        super(description);
        this.date = date;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public String getType() {
        return IDENTIFIER;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.date.format(FORMATTER));
    }
}
