import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    public LocalDate eventDate;

    public Event(String eventName) {
        super(eventName.substring(6, eventName.indexOf("/at")));
        int start = eventName.indexOf("/at");
        this.eventDate = LocalDate.parse(eventName.substring(start + 4));
    }

    public Event(String eventName, boolean isDone) {
        super(eventName.substring(0, eventName.indexOf("(at:")), isDone);
        int start = eventName.indexOf("(at:") + 5;
        this.eventDate = LocalDate.parse(eventName.substring(start, start + 11),
                DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + "(at: "
                + this.eventDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
    }
}
