package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private static final String TYPE = "D";
    private static final DateTimeFormatter DATABASE_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private LocalDateTime deadline;

    public Deadline(String description) {
        super(description);
    }

    public Deadline(String description, String due) {
        super(description);
        this.deadline = LocalDateTime.parse(due, DATABASE_DATE_TIME_FORMAT);
    }

    public Deadline(String description, String deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = LocalDateTime.parse(deadline, DATABASE_DATE_TIME_FORMAT);
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
                + formatDate(deadline)[0] + " " + formatDate(deadline)[1];
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by: %s)", getLabel(), getStatusIcon(),
                this.description, formatDate(deadline)[0] + " " + formatDate(deadline)[1]);
    }
}
