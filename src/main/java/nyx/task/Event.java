package nyx.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import nyx.NyxException;

/**
 * Represents an event that occurs at a specific date and time.
 * This class inherits from the parent abstract Task class.
 */
public class Event extends Task {
    private LocalDateTime at;

    /**
     * Constructs an event with its description, datetime, and an indicator of whether it is marked as done.
     * @param content Description of the event.
     * @param at Datetime at which the event occurs.
     * @param isDone Indicator to indicate whether the deadline task is done.
     * @throws NyxException If the wrong datetime format is given.
     */
    public Event(String content, String at, boolean isDone) throws NyxException {
        super(content, isDone);
        this.at = DateTimeHandler.parseDateTime(at);
    }

    /**
     * Constructs an uncompleted event with its description, datetime.
     * @param content Description of the event.
     * @param at Datetime at which the event occurs.
     * @throws NyxException If the wrong datetime format is given.
     */
    public Event(String content, String at) throws NyxException {
        this(content, at, false);
    }

    /**
     * Returns a String representation of the event in the format required for saving into hard disk.
     * @return String representation of the event in the format required to save into hard disk.
     */
    @Override
    public String formatData() {
        String dateFormat = at.format(DateTimeFormatter.ofPattern(DateTimeHandler.DATETIME_FORMAT));
        return String.format("E, %d, %s, %s\n", getStatusInt(), getContent(), dateFormat);
    }

    /**
     * Changes the datetime associated with the Event task.
     * @param newDateTime New datetime to change to.
     * @throws NyxException If the wrong datetime format is given.
     */
    public void changeDateTime(String newDateTime) throws NyxException {
        this.at = DateTimeHandler.parseDateTime(newDateTime);
    }

    /**
     * Returns the String representation of the event.
     * @return String representation of the event.
     */
    @Override
    public String toString() {
        String dateFormat = at.format(DateTimeFormatter.ofPattern("EEE d MMM yyyy, h:ma"));
        return "[E]" + super.toString() + " (at: " + dateFormat + ")";
    }
}
