package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Event extends Task {
    private static final String symbol = "[E]";
    private final LocalDateTime deadline;

    public Event(String action, LocalDateTime deadline) {
        super(action);
        this.deadline = deadline;
    }

    @Override
    public String toSaveFormat() {
        return String.format("%s||%s||%s||%s", symbol, super.isComplete(), super.getAction(),
                this.deadline.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm")));
    }

    @Override
    public String toString() {
        return String.format("%s%s (at: %s)", symbol, super.toString(),
                this.deadline.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)));
    }
}
