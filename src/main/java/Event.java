import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private static final String type = "E";
    private static final DateTimeFormatter DATABASE_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private LocalDateTime due;

    public Event(String description, String due) {
        super(description);
        this.due = LocalDateTime.parse(due, DATABASE_DATE_TIME_FORMAT);
    }

    private String[] formatDate(LocalDateTime date) {
        return date.format(DATABASE_DATE_TIME_FORMAT).split(" ");
    }

    public Event(String description, boolean isDone) {
        super(description, isDone);
    }

    public String getLabel() {
        return type;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (at: %s)", getLabel(), getStatusIcon(),
                this.description, formatDate(due)[0] + " " + formatDate(due)[1]);
    }
}
