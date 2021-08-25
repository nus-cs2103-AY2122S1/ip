import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    public Event(String description, boolean isDone, String at) {
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
        String[] info = {"E", this.isDone ? "1" : "0", this.description, this.at};
        return String.join(" | ", info);
    }
}
