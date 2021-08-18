package task;

import java.time.format.DateTimeFormatter;

/**
 * The is the Event class that extends from Task.
 * Event task starts at a specific time and ends at a specific time.
 *
 * @author  HU JIAJUN
 * @version %I%, %G%
 * @since   1.0
 */

public class Event extends Task {
    public static final String ID = "E";
    private static final String PRINT_DATE_PATTERN = "MMM dd yyyy";
    private static final String PRINT_TIME_PATTERN = "hh:mm a";
    private static final String SAVE_DATE_PATTERN = "yyyy-MM-dd";
    private static final String SAVE_TIME_PATTERN = "HH:mm";
    private final EventDateTime atDateTime;

    /**
     * This is constructor method of Event.
     *
     * @param name       task name
     * @param atDateTime a specific date, start time and end time of task
     */
    public Event(String name, EventDateTime atDateTime) {
        super(name);
        this.atDateTime = atDateTime;
    }

    /**
     * This is constructor method of Event.
     *
     * @param name       task name
     * @param atDateTime a specific date, start time and end time of task
     * @param isDone     task status: done or not done
     */
    public Event(String name, EventDateTime atDateTime, boolean isDone) {
        super(name, isDone);
        this.atDateTime = atDateTime;
    }

    /**
     * Get the specific date, start time and end time of task.
     *
     * @return the specific date, start time and end time of task
     */
    public EventDateTime getAtDateTime() {
        return atDateTime;
    }

    private String formatPrintDateTime() {
        return atDateTime.getAtDate().format(DateTimeFormatter.ofPattern(PRINT_DATE_PATTERN)) + " " +
                atDateTime.getStartTime().format(DateTimeFormatter.ofPattern(PRINT_TIME_PATTERN)) + "-" +
                atDateTime.getEndTime().format(DateTimeFormatter.ofPattern(PRINT_TIME_PATTERN));
    }

    private String formatSaveDateTime() {
        return atDateTime.getAtDate().format(DateTimeFormatter.ofPattern(SAVE_DATE_PATTERN)) + " " +
            atDateTime.getStartTime().format(DateTimeFormatter.ofPattern(SAVE_TIME_PATTERN)) + "-" +
            atDateTime.getEndTime().format(DateTimeFormatter.ofPattern(SAVE_TIME_PATTERN));
    }

    /**
     * Format Task to String array:
     * If task is done, [E, 0, Task1, DateTime]; else, [E, 1, Task1, DateTime]
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
    public Event markAsDone() {
        return new Event(super.getName(), atDateTime, true);
    }

    /**
     * Print task with format:
     * If task is done, [E][X] Task1; else, [E][ ] Task1.
     */
    @Override
    public String toString() {
        return "[" + ID + "]" + super.toString() + " (at: " + formatPrintDateTime() + ")";
    }
}
