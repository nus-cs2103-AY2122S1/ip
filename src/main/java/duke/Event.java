package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private final LocalDateTime duration;

    /**
     * Constructor for Event class.
     * @param taskName Name of task.
     * @param duration Duration of task.
     * @throws DateTimeParseException If deadline cannot be parsed.
     */
    public Event(String taskName, String duration) throws DateTimeParseException {
        super(taskName);
        this.duration = DukeDateFormatter.stringToDateTime(duration.trim());
    }
    /**
     * Constructor for Event class.
     * @param taskName Name of task.
     * @param isDone True implies task has been done.
     * @param duration Duration of task.
     * @throws DateTimeParseException If deadline cannot be parsed.
     */
    public Event(String taskName, boolean isDone, String duration) throws DateTimeParseException {
        super(taskName, isDone);
        this.duration = DukeDateFormatter.stringToDateTime(duration.trim());
    }

    public LocalDateTime getDuration() {
        return this.duration;
    }

    @Override
    public String getFileString() {
        return String.format("E | %s | %s", super.getFileString(), DukeDateFormatter.dateTimeToFile(this.duration));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), DukeDateFormatter.dateTimeToString(this.duration));
    }

}
