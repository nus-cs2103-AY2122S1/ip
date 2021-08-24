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

    public Event(String description, boolean isDone, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(description, isDone);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * Encode Event in format
     * "taskType isDone description startDateTime endDateTime"
     * @return encoded event string
     */
    public String encode() {
        return String.format("E %b %s /at %s %s",
                isDone,
                content,
                startDateTime
                        .format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")),
                endDateTime
                        .format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"))
                );
    }

    public String toString() {
        return String.format("[E] [%s] %s (at: %s)",
                isDone ? "X" : " ",
                content,
                startDateTime
                        .format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm"))
                        + " - "
                        + endDateTime.format(
                                DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm")));
    }
}

