import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    public Event(String description, boolean isDone, LocalDateTime at) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        String dateString = at.format(format);
        return "[E]" + super.toString() + " (at: " + dateString + ")";
    }

    @Override
    String printFormat() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        String dateString = at.format(format);
        String[] info = {"E", this.isDone ? "1" : "0", this.description, dateString};
        return String.join(" | ", info);
    }
}
