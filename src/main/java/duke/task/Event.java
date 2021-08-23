package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDateTime time;

    public Event(String taskName, LocalDateTime time) {
        super(taskName);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + time.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")) + ")";
    }
}