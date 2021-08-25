package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The {@code EventTask} class extends from {@code Task} to contain an additional
 * {@code LocalDateTime time} field.
 */
public class EventTask extends Task {
    protected LocalDateTime time;
    static DateTimeFormatter TIME_DISPLAY_FORMAT = DateTimeFormatter.ofPattern("d MMM y, E, kk:mm");

    public EventTask(String description, LocalDateTime time) {
        super(description);
        this.time = time;
    }
    public EventTask(String description, LocalDateTime time, boolean isDone) {
        super(description, isDone);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time.format(TIME_DISPLAY_FORMAT) + ")";
    }
}