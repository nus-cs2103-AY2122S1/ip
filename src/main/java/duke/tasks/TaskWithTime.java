package duke.tasks;

import java.time.LocalDateTime;

/**
 * The {@code TaskWithTime} class extends from {@code Task} to additionally include a {@code LocalDateTime time} field.
 */
public abstract class TaskWithTime extends Task {

    protected LocalDateTime time;

    /**
     * Initializes a {@code TaskWithTime} with {@code String description} and {@code LocalDateTime time}.
     *
     * @param description {@code String} Description of task.
     * @param time {@code LocalDateTime} Time associated with the task.
     */
    public TaskWithTime(String description, LocalDateTime time) {
        super(description);
        this.time = time;
    }

    /**
     * Initializes a {@code TaskWithTime} with {@code String description} and {@code LocalDateTime time}.
     *
     * @param description {@code String} Description of task.
     * @param time {@code LocalDateTime} Time associated with the task.
     * @param isDone {@code boolean} Done status of task.
     */
    public TaskWithTime(String description, LocalDateTime time, boolean isDone) {
        super(description, isDone);
        this.time = time;
    }

    /**
     * Gets the time value of this {@code TaskWithTime}.
     *
     * @return {@LocalDateTime time} field.
     */
    public LocalDateTime getTime() {
        return this.time;
    }

    /**
     * Compares whether given {@code LocalDateTime} falls on the same date as the {@code time}
     * of this {@code TaskWithTime}.
     *
     * @param date {@code LocalDateTime} to compare against
     * @return {@code boolean} representing whether the two dates match.
     */
    @Override
    public boolean isMatch(LocalDateTime date) {
        return this.isSameDateAs(date);
    }

    private boolean isSameDateAs(LocalDateTime otherDate) {
        return otherDate.getYear() == this.time.getYear()
                && otherDate.getMonth() == this.time.getMonth()
                && otherDate.getDayOfMonth() == this.time.getDayOfMonth();
    }

    /**
     * Compares the {@code LocalDateTime} value of this {@code TaskWithTime} with another.
     */
    public int compareTo(TaskWithTime otherTask) {
        return otherTask.getTime().compareTo(this.time);
    }
}
