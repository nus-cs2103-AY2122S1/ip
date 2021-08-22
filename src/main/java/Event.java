import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private static final char TASK_LETTER = 'E';
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private LocalDate date;
    private String timeRange;

    public Event(String description, LocalDate date, String timeRange) {
        super(description);
        this.date = date;
        this.timeRange = timeRange;
    }

    public Event(String description, LocalDate date, String timeRange, boolean isDone) {
        super(description, isDone);
        this.date = date;
        this.timeRange = timeRange;
    }

    @Override
    public String toString() {
        return String.format("[%c]%s (at: %s %s)", Event.TASK_LETTER,
                super.toString(), this.date.format(Event.DATE_TIME_FORMATTER), this.timeRange);
    }

    @Override
    public String stringToStore() {
        return Event.TASK_LETTER + " | " + this.getStatusIcon() + " | " + this.description + " | "
                + this.date.format(Event.DATE_TIME_FORMATTER) + " | " + this.timeRange + "\n";
    }
}
