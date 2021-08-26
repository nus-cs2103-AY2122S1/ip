import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    LocalDateTime at;

    public Event(String name, LocalDateTime at) {
        super(name);
        this.at = at;
    }


    public Event(String name, LocalDateTime at, boolean isDone) {
        super(name, isDone);
        this.at = at;
    }

    @Override
    public String toStringInStorage() {
        int done = this.isDone ? 1 : 0;
        return String.format("E/%d/%s/%s", done, this.name, this.at.toString().replace("T", " "));
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy K:mm a");
        return String.format("[E]%s(at:%s)", super.toString(), this.at.format(formatter));
    }
}
