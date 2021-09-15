package duke.tasks;

import java.time.LocalDateTime;

/**
 * The {@code DeadlineTask} class extends from {@code TaskWithTime}.
 */
public class DeadlineTask extends TaskWithTime {
    protected LocalDateTime time;

    /**
     * Initializes a {@code DeadlineTask} with {@code String description} and {@code LocalDateTime time}.
     *
     * @param description {@code String} Description of task.
     * @param time {@code LocalDateTime} Time that the task is due by.
     */
    public DeadlineTask(String description, LocalDateTime time) {
        super(description, time);
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
        super(description, time, isDone);
        this.time = time;
    }

    /**
     * Checks if given {@code TaskTypes} query matches this {@code Task}.
     *
     * @param query {@code TaskTypes} to be compared against this {@code Task}
     * @return {@code boolean} value representing a match
     */
    public boolean isMatch(TaskTypes query) {
        return query.equals(TaskTypes.DEADLINE);
    }

    @Override
    public String getType() {
        return "[D]";
    }

    /**
     * Prints {@code DeadlineTask} description with the prefix [D] and deadline time.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + time.format(Task.TIME_DISPLAY_FORMAT) + ")";
    }
}
