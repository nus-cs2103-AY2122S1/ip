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
    private static final String DATE_PATTERN = "MMM dd yyyy";
    private static final String TIME_PATTERN = "hh:mm a";

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

    private String formatDateTime() {
        return atDateTime.getAtDate().format(DateTimeFormatter.ofPattern(DATE_PATTERN)) + " " +
                atDateTime.getStartTime().format(DateTimeFormatter.ofPattern(TIME_PATTERN)) + "-" +
                atDateTime.getEndTime().format(DateTimeFormatter.ofPattern(TIME_PATTERN));
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
        return "[E]" + super.toString() + " (at: " + formatDateTime() + ")";
    }
}
