package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDateTime dateTime;

    public Event(boolean done, String taskName, LocalDateTime dateTime) {
        super(done, taskName);
        this.dateTime = dateTime;
    }

    @Override
    public String encode() {
        return String.format("E|%s|%s", super.encode(), dateTime);
    }

    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM d yyyy HHmm");
        return String.format("[E]%s (at: %s)", super.toString(), this.dateTime.format(dateTimeFormatter));
    }
}
