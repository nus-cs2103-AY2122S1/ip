package nyx;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event that occurs at a specific date and time.
 * This class inherits from the parent abstract Task class.
 */
public class Event extends Task {
    private static final String DATETIME_FORMAT = "yyyy-MM-dd H:m";
    private final LocalDateTime at;

    /**
     * Constructs an event with its description, datetime, and an indicator of whether it is marked as done.
     * @param content Description of the event.
     * @param at Datetime at which the event occurs.
     * @param isDone Indicator to indicate whether the deadline task is done.
     */
    public Event(String content, String at, boolean isDone) {
        super(content, isDone);
        this.at = LocalDateTime.parse(at, DateTimeFormatter.ofPattern(DATETIME_FORMAT));
    }

    /**
     * Constructs an uncompleted event with its description, datetime.
     * @param content Description of the event.
     * @param at Datetime at which the event occurs.
     */
    public Event(String content, String at) {
        this(content, at, false);
    }

    /**
     * Returns a String representation of the event in the format required for saving into hard disk.
     * @return String representation of the event in the format required to save into hard disk.
     */
    @Override
    public String dataFormat() {
        String dateFormat =  at.format(DateTimeFormatter.ofPattern(DATETIME_FORMAT));
        return String.format("E, %d, %s, %s\n", getStatusInt(), getContent(), dateFormat);
    }

    /**
     * Returns the String representation of the event.
     * @return String representation of the event.
     */
    @Override
    public String toString() {
        String dateFormat = at.format(DateTimeFormatter.ofPattern("EEE d MMM yyyy, K:ma"));
        return "[E]" + super.toString() + " (at: " + dateFormat + ")";
    }
}