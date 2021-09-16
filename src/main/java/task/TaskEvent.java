package task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;

/**
 * Event task event.
 */
public class TaskEvent extends Task {
    private final LocalDate at;
    private final String time;

    /**
     * Constructor.
     *
     * @param description Description of task.
     * @param date Date of Event.
     * @param time Time of Event. Optional, "" otherwise.
     * @param done Completion status.
     */
    public TaskEvent(String description, LocalDate date, String time, boolean done) {
        super(description, done);
        this.at = date;
        this.time = Optional.ofNullable(time)
                .map(String::strip)
                .orElse("");

        if (checkInvalidTime(this.time)) {
            throw new DateTimeParseException("INVALID TIME", this.time, 0);
        }
    }

    /**
     * Return string representation of Event.
     *
     * @return Event display.
     */
    @Override
    public String toString() {
        String checkBox = isDone
                ? "[X] "
                : "[ ] ";
        return "[E]" + checkBox + description
                + " (at: " + at + (!time.equals("") ? " "
                + time : "") + ")";
    }

    /**
     * Returns the string representation of Task to be used for saving.
     *
     * @return Save string.
     */
    @Override
    public String saveString() {
        return "E" + '\t'
                + (this.isDone ? "1" : "0") + '\t'
                + this.description + '\t'
                + this.at + '\t'
                + this.time;
    }

    /**
     * Checks if the task falls on a given date (if applicable).
     *
     * @param date Date to check.
     * @return Whether task is tagged to the passed date.
     */
    @Override
    public boolean isDate(LocalDate date) {
        return date.equals(at);
    }

    @Override
    public LocalDate getDate() {
        return at;
    }

    @Override
    public String getTime() {
        return time;
    }
}
