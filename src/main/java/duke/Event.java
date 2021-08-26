package duke;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected final LocalDateTime dateTime;

    public Event(String description, String dateTime) {
        super(description);
        this.dateTime = convert(dateTime);
    }

    private LocalDateTime convert(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
        return LocalDateTime.parse(dateTime, formatter);
    }

    @Override
    public String toString() {
        return "[E][" + this.getStatusIcon() + "] " + this.getDescription() + " (at: "
                + this.dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")) + ")";
    }
}