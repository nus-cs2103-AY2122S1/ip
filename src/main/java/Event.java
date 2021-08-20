import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime time;

    public Event(String name, LocalDateTime time) {
        super(name);
        this.time = time;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d h.mm a");
        return String.format("[E]%s(at: %s)", super.toString(), time.format(formatter));
    }
}
