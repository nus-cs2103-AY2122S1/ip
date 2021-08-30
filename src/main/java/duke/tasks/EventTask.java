package duke.tasks;

import java.time.LocalDateTime;

/**
 * The {@code EventTask} class extends from {@code Task} to contain an additional
 * {@code LocalDateTime time} field.
 */
public class EventTask extends Task {
    protected LocalDateTime time;

    /**
     * Initializes an {@code EventTask} with {@code String description} and {@code LocalDateTime time}.
     *
     * @param description {@code String} Description of task.
     * @param time {@code LocalDateTime} Time that the task takes place at.
     */
    public EventTask(String description, LocalDateTime time) {
        super(description);
        this.time = time;
    }

    /**
     * Initializes an {@code DeadlineTask} with {@code String description}, {@code LocalDateTime time}
     * and {@code boolean isDone} status.
     *
     * @param description {@code String} Description of task.
     * @param time {@code LocalDateTime} Time that the task takes place at.
     * @param isDone {@code boolean} Done status of task.
     */
    public EventTask(String description, LocalDateTime time, boolean isDone) {
        super(description, isDone);
        this.time = time;
    }

    /**
     * Prints {@code EventTask} description with the prefix [E] and event time.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time.format(TIME_DISPLAY_FORMAT) + ")";
    }
}
