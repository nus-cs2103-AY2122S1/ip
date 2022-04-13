package task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import duke.util.DateTimeUtils;

/**
 * Deadline task event.
 */
public class TaskDeadline extends Task {
    private final LocalDate by;
    private final String time;

    /**
     * Constructor.
     *
     * @param description Description of task.
     * @param date Date of task.
     * @param time Time of task (Optional argument), saved as "" otherwise.
     * @param done Completion status.
     */
    public TaskDeadline(String description, LocalDate date, String time, boolean done, LocalDateTime dateTimeAdded)
            throws DateTimeParseException {
        super(description, done, dateTimeAdded);
        this.by = date;
        this.time = Optional.ofNullable(time)
                .map(String::strip)
                .orElse("");

        if (DateTimeUtils.checkInvalidTime(this.time)) {
            throw new DateTimeParseException("INVALID TIME", this.time, 0);
        }
    }

    /**
     * Returns string representation of Deadline.
     *
     * @return Deadline display.
     */
    @Override
    public String toString() {
        String checkBox = isDone
                ? "[X] "
                : "[ ] ";
        return "[D]" + checkBox + description
                + " (by: " + by + (!time.equals("") ? " "
                + time : "") + ")";
    }

    /**
     * Returns the string representation of Task to be used for saving.
     *
     * @return Save string.
     */
    @Override
    public String saveString() {
        return "D" + '\t'
                + (this.isDone ? "1" : "0") + '\t'
                + this.description + '\t'
                + this.by + '\t'
                + this.time + '\t'
                + this.dateTimeAdded;
    }

    /**
     * Checks if the task falls on a given date (if applicable).
     *
     * @param date Date to check.
     * @return Whether task is tagged to the passed date.
     */
    @Override
    public boolean isDate(LocalDate date) {
        return date.equals(by);
    }

    /**
     * Returns a LocalDate of the deadline.
     *
     * @return LocalDate of which the deadline is due.
     */
    @Override
    public LocalDate getDate() {
        return by;
    }

    /**
     * Returns a String representing time of deadline if present.
     *
     * @return Time of which deadline is due, "" if no time is present
     */
    @Override
    public String getTime() {
        return time;
    }
}
