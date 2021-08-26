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

    /**
     * Initializes a {@code DeadlineTask} with {@code String description} and {@code LocalDateTime time}.
     *
     * @param description {@code String} Description of task.
     * @param time {@code LocalDateTime} Time that the task is due by.
     */
    public DeadlineTask(String description, LocalDateTime time) {
        super(description);
        this.time = time;
    }

    /**
     * Initializes a {@code DeadlineTask} with {@code String description}, {@code LocalDateTime time}
     * and {@code boolean isDone} status.
     *
     * @param description {@code String} Description of task.
     * @param time {@code LocalDateTime} Time that the task is due by.
     * @param isDone {@code boolean} Done status of task.
     */
    public DeadlineTask(String description, LocalDateTime time, boolean isDone) {
        super(description, isDone);
        this.time = time;
    }

    /**
     * Prints {@code DeadlineTask} description with the prefix [D] and deadline time.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + time.format(TIME_DISPLAY_FORMAT) + ")";
    }
}