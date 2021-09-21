package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This is the Deadline class that extends from Task.
 * Deadline task needs to be done before a specific time.
 */
public class Deadline extends Task {
    public static final String ID = "D";
    private static final String PRINT_DATE_PATTERN = "MMM dd yyyy";
    private static final String PRINT_TIME_PATTERN = "hh:mm a";
    private static final String SAVE_DATE_PATTERN = "yyyy-MM-dd";
    private static final String SAVE_TIME_PATTERN = "HH:mm";
    private final LocalDateTime byDateTime;

    /**
     * Constructs a Deadline object.
     *
     * @param name Task name.
     * @param byDateTime A specific date and time of task that needs to be done before it.
     */
    public Deadline(String name, LocalDateTime byDateTime) {
        super(name);
        this.byDateTime = byDateTime;
    }

    /**
     * This is constructor method of Deadline.
     *
     * @param name Task name.
     * @param byDateTime A specific date and time of task that needs to be done before it.
     * @param isDone Task status: done or not done.
     */
    public Deadline(String name, LocalDateTime byDateTime, boolean isDone) {
        super(name, isDone);
        this.byDateTime = byDateTime;
    }

    /**
     * Returns the specific date and time of task that needs to be done before it.
     *
     * @return The specific date and time of task that needs to be done before it.
     */
    public LocalDateTime getByDateTime() {
        return byDateTime;
    }

    private String formatPrintDateTime() {
        String date = byDateTime.format(DateTimeFormatter.ofPattern(PRINT_DATE_PATTERN));
        String time = byDateTime.format(DateTimeFormatter.ofPattern(PRINT_TIME_PATTERN))
                .toUpperCase();
        return date + " " + time;
    }

    private String formatSaveDateTime() {
        String date = byDateTime.format(DateTimeFormatter.ofPattern(SAVE_DATE_PATTERN));
        String time = byDateTime.format(DateTimeFormatter.ofPattern(SAVE_TIME_PATTERN));
        return date + " " + time;
    }

    /**
     * Formats Task to String array.
     * If task is done, [D, 0, Task1, DateTime]; else, [D, 1, Task1, DateTime].
     *
     * @return Task in String array.
     */
    @Override
    public String[] formatTask() {
        String done;
        if (isDone()) {
            done = "0";
        } else {
            done = "1";
        }
        return new String[] {ID, done, super.getName(), formatSaveDateTime()};
    }

    /**
     * Returns task with done status.
     *
     * @return Task with done status.
     */
    @Override
    public Deadline markAsDone() {
        return new Deadline(super.getName(), getByDateTime(), true);
    }

    /**
     * Prints task.
     * If task is done, [D][X] Task1; else, [D][ ] Task1.
     */
    @Override
    public String toString() {
        return "[" + ID + "]" + super.toString() + " (by: " + formatPrintDateTime() + ")";
    }
}
