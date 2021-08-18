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
    public static final String ID = "D";
    private static final String PRINT_DATE_PATTERN = "MMM dd yyyy";
    private static final String PRINT_TIME_PATTERN = "hh:mm a";
    private static final String SAVE_DATE_PATTERN = "yyyy-MM-dd";
    private static final String SAVE_TIME_PATTERN = "HH:mm";
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

    private String formatPrintDateTime() {
        return byDateTime.format(DateTimeFormatter.ofPattern(PRINT_DATE_PATTERN)) + " " +
                byDateTime.format(DateTimeFormatter.ofPattern(PRINT_TIME_PATTERN));
    }

    private String formatSaveDateTime() {
        return byDateTime.format(DateTimeFormatter.ofPattern(SAVE_DATE_PATTERN)) + " " +
            byDateTime.format(DateTimeFormatter.ofPattern(SAVE_TIME_PATTERN));
    }

    /**
     * Format Task to String array:
     * If task is done, [D, 0, Task1, DateTime]; else, [D, 1, Task1, DateTime]
     *
     * @return Task in String array
     */
    @Override
    public String[] formatTask() {
        String done;
        if (isDone()) {
            done = "0";
        } else {
            done = "1";
        }
        return new String[]{ID, done, super.getName(), formatSaveDateTime()};
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
        return "[" + ID + "]" + super.toString() + " (by: " + formatPrintDateTime() + ")";
    }
}
