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
}
