package duke.task;

import java.time.format.DateTimeFormatter;

/**
 * This is the Event class that extends from Task.
 * Event task starts at a specific time and ends at a specific time.
 */
public class Event extends Task {
    public static final String ID = "E";
    private static final String PRINT_DATE_PATTERN = "MMM dd yyyy";
    private static final String PRINT_TIME_PATTERN = "hh:mm a";
    private static final String SAVE_DATE_PATTERN = "yyyy-MM-dd";
    private static final String SAVE_TIME_PATTERN = "HH:mm";
    private final EventDateTime atDateTime;

    /**
     * Constructs a Event object.
     *
     * @param name Task name.
     * @param atDateTime A specific date, start time and end time of task.
     */
    public Event(String name, EventDateTime atDateTime) {
        super(name);
        this.atDateTime = atDateTime;
    }

    /**
     * Constructs a Event object.
     *
     * @param name Task name.
     * @param atDateTime A specific date, start time and end time of task.
     * @param isDone Task status: done or not done.
     */
    public Event(String name, EventDateTime atDateTime, boolean isDone) {
        super(name, isDone);
        this.atDateTime = atDateTime;
    }

    /**
     * Returns the specific date, start time and end time of task.
     *
     * @return The specific date, start time and end time of task.
     */
    public EventDateTime getAtDateTime() {
        return atDateTime;
    }

    private String formatPrintDateTime() {
        String date = atDateTime.getAtDate()
                .format(DateTimeFormatter.ofPattern(PRINT_DATE_PATTERN));
        String startTime = atDateTime.getStartTime()
                .format(DateTimeFormatter.ofPattern(PRINT_TIME_PATTERN)).toUpperCase();
        String endTime = atDateTime.getEndTime()
                .format(DateTimeFormatter.ofPattern(PRINT_TIME_PATTERN)).toUpperCase();
        return date + " " + startTime + "-" + endTime;
    }

    private String formatSaveDateTime() {
        String date = atDateTime.getAtDate()
                .format(DateTimeFormatter.ofPattern(SAVE_DATE_PATTERN));
        String startTime = atDateTime.getStartTime()
                .format(DateTimeFormatter.ofPattern(SAVE_TIME_PATTERN));
        String endTime = atDateTime.getEndTime()
                .format(DateTimeFormatter.ofPattern(SAVE_TIME_PATTERN));
        return date + " " + startTime + "-" + endTime;
    }

    /**
     * Formats Task to String array.
     * If task is done, [E, 0, Task1, DateTime]; else, [E, 1, Task1, DateTime].
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
    public Event markAsDone() {
        return new Event(super.getName(), atDateTime, true);
    }

    /**
     * Prints task.
     * If task is done, [E][X] Task1; else, [E][ ] Task1.
     */
    @Override
    public String toString() {
        return "[" + ID + "]" + super.toString() + " (at: " + formatPrintDateTime() + ")";
    }
}
