package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private static final String TYPE = "E";
    private static final DateTimeFormatter DATABASE_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private LocalDateTime date;

    public Event(String description) {
        super(description);
    }

    public Event(String description, String date) {
        super(description);
        this.date = LocalDateTime.parse(date, DATABASE_DATE_TIME_FORMAT);
    }

    public Event(String description, boolean isDone) {
        super(description, isDone);
    }

    public Event(String description, String date, boolean isDone) {
        super(description, isDone);
        this.date = LocalDateTime.parse(date, DATABASE_DATE_TIME_FORMAT);
    }

    private String[] formatDate(LocalDateTime date) {
        return date.format(DATABASE_DATE_TIME_FORMAT).split(" ");
    }

    public String getLabel() {
        return TYPE;
    }

    @Override
    public String databaseString() {
        return TYPE + " | " + super.databaseString() + " | "
                + formatDate(date)[0] + " " + formatDate(date)[1];
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (at: %s)", getLabel(), getStatusIcon(),
                this.description, formatDate(date)[0] + " " + formatDate(date)[1]);
    }
}
