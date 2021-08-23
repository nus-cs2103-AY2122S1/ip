package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    LocalDateTime startDateTime;
    LocalDateTime endDateTime;

    public Event(String description, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(description);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public String toString() {
        return String.format("[E] [%s] %s (at: %s)",
                isDone ? "X" : " ",
                content,
                startDateTime
                        .format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"))
                        + " - "
                        + endDateTime.format(
                                DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")));
    }
}

