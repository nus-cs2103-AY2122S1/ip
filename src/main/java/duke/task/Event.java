package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private String startTime;

    public Event(String str, LocalDateTime startTime) {
        super(str, startTime);
        DateTimeFormatter dateOnly = DateTimeFormatter.ofPattern("MMM d, yyyy HH:mm");
        this.startTime = dateOnly.format(startTime);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (on: " + startTime + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Event)) {
            return false;
        }
        Event other = (Event) obj;
        return this.startTime.equals(other.startTime);
    }
}
