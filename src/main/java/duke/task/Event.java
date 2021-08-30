package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected final LocalDateTime at;

    public Event(String description, boolean isDone, LocalDateTime at) {
        super(description, isDone, TaskType.EVENT);
        this.at = at;
    }

    public String getEventTime() {
        return "(at: " + at.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")) + ")";
    }

    @Override
    public String getStatus() {
        return "[E]" + super.getStatus() + " " + getEventTime();
    }

    @Override
    public String toString() {
        return "E" + " | " + super.toString() + " | " + at;
    }
}
