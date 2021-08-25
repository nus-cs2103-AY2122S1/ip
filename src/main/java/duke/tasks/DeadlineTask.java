package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The {@code DeadlineTask} class extends from {@code Task} to contain an additional
 * {@code LocalDateTime time} field.
 */
public class DeadlineTask extends Task {
    protected LocalDateTime time;
    static DateTimeFormatter TIME_DISPLAY_FORMAT = DateTimeFormatter.ofPattern("d MMM y, E, kk:mm");

    public DeadlineTask(String description, LocalDateTime time) {
        super(description);
        this.time = time;
    }

    public DeadlineTask(String description, LocalDateTime time, boolean isDone) {
        super(description, isDone);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + time.format(TIME_DISPLAY_FORMAT) + ")";
    }
}