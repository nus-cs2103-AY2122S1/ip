package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

public class Event extends Task {
    private final static String symbol = "[E]";
    private final LocalDateTime deadline;

    public Event(String action, LocalDateTime deadline) throws DateTimeParseException {
        super(action);
        this.deadline = deadline;
    }

    public String toSaveFormat() {
        return String.format("%s||%s||%s||%s", symbol, super.isComplete(), super.getAction(),
                this.deadline.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm")));
    }

    public String toString() {
        return String.format("%s%s (at: %s)", symbol, super.toString(),
                this.deadline.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)));
    }
}
