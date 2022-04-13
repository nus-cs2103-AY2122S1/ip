package duke.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * The {@code Task} parent class contains abstractions for tasks.
 */
public abstract class Task {
    public static final DateTimeFormatter TIME_DISPLAY_FORMAT = DateTimeFormatter.ofPattern("d MMM y, E, kk:mm");
    protected String description;
    protected boolean isDone = false;

    /**
     * Initializes an instance of {@code Task} with {@code String description}.
     *
     * @param description {@code String} that contains description of task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Initializes an instance of {@code Task} with {@code String description}
     * and {@code boolean isDone} status.
     *
     * @param description {@code String} that contains description of task.
     * @param isDone {@code boolean} that represents task completion status.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Marks the current {@code Task} to be done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns description of {@code Task}.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets the time value of this {@code Task} (for sorting).
     * Since {@code Task}s do not have time, it has the minimum possible value.
     */
    public LocalDateTime getTime() {
        return LocalDateTime.of(LocalDate.MIN, LocalTime.MIN);
    }

    /**
     * Returns type of {@code Task}.
     */
    public abstract String getType();

    @Override
    public String toString() {
        String checkbox = isDone ? "[X]" : "[ ]";
        return checkbox + " " + description;
    }

    /**
     * Checks if given {@code String} query matches this {@code Task}.
     *
     * @param queryString {@code String} to be compared against this {@code Task}'s description.
     * @return {@code boolean} value representing a match
     */
    public boolean isMatch(String queryString) {
        return this.description.contains(queryString);
    }

    /**
     * Checks if given {@code TaskStatuses} query matches this {@code Task}.
     *
     * @param queryStatus {@code String} to be compared against this {@code Task}'s {@code isDone} status.
     * @return {@code boolean} value representing a match
     */
    public boolean isMatch(TaskStatuses queryStatus) {
        return (queryStatus.equals(TaskStatuses.ISDONE) && this.isDone)
                || (queryStatus.equals(TaskStatuses.ISNOTDONE) && !this.isDone);
    }

    /**
     * Checks if given {@code String} query matches this {@code LocalDateTime}.
     * Since {@code Task}s have no time, false is returned by default.
     *
     * @param queryDateTime {@code LocalDateTime} to be compared against this {@code Task}.
     * @return {@code false}, since {@code Task} objects have no time to be compared against.
     */
    public boolean isMatch(LocalDateTime queryDateTime) {
        return false;
    };

    /**
     * Checks if given {@code TaskTypes} matches this {@code Task}.
     */
    public abstract boolean isMatch(TaskTypes queryTaskType);

}
