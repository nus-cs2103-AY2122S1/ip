import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    LocalDateTime at;

    public Event(String name, LocalDateTime at) {
        super(name);
        this.at = at;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm a");
        return String.format("[E]%s(at:%s)", super.toString(), this.at.format(formatter));
    }
}
