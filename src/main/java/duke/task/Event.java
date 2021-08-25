package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime at;

    public Event(String taskName, boolean isDone, LocalDateTime at) {
        super(taskName, isDone);
        this.at = at;
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yyy hh:mm a");
        return "[E]" + super.toString() + " (at: " + this.at.format(dtf) + ")";
    }
}
