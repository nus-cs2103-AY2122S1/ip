package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The is the Deadline class that extends from Task.
 * Deadline task needs to be done before a specific time.
 *
 * @author  HU JIAJUN
 * @version %I%, %G%
 * @since   1.0
 */

public class Deadline extends Task {
    private static final String DATE_PATTERN = "MMM dd yyyy";
    private static final String TIME_PATTERN = "hh:mm a";

    private final LocalDateTime byDateTime;

    /**
     * This is constructor method of Deadline.
     *
     * @param name   task name
     * @param byDateTime a specific date and time of task that needs to be done before it
     */
    public Deadline(String name, LocalDateTime byDateTime) {
        super(name);
        this.byDateTime = byDateTime;
    }

    /**
     * This is constructor method of Deadline.
     *
     * @param name   task name
     * @param byDateTime a specific date and time of task that needs to be done before it
     * @param isDone task status: done or not done
     */
    public Deadline(String name, LocalDateTime byDateTime, boolean isDone) {
        super(name, isDone);
        this.byDateTime = byDateTime;
    }

    /**
     * Get the specific date and time of task that needs to be done before it.
     *
     * @return the specific date and time of task that needs to be done before it
     */
    public LocalDateTime getByDateTime() {
        return byDateTime;
    }

    private String formatDateTime() {
        return byDateTime.format(DateTimeFormatter.ofPattern(DATE_PATTERN)) + " " +
                byDateTime.format(DateTimeFormatter.ofPattern(TIME_PATTERN));
    }

    /**
     * Mark task status as done.
     *
     * @return task with done status
     */
    @Override
    public Deadline markAsDone() {
        return new Deadline(super.getName(), getByDateTime(), true);
    }

    /**
     * Print task with format:
     * If task is done, [D][X] Task1; else, [D][ ] Task1.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDateTime() + ")";
    }
}
