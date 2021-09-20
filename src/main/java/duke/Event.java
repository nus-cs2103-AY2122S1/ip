package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Represents a task with a specific date which it is occurring.
 */
public class Event extends Task{
    protected LocalDate eventDate;

    /**
     * Constructor for event task.
     *
     * @param description The string description of event.
     * @param eventDate The LocalDate of a specific day.
     * @param isDone The completion status of the task.
     */
    public Event(String description, LocalDate eventDate, boolean isDone) {
        super(description, isDone);
        this.eventDate = eventDate;
    }

    private String formatDate() {
        return eventDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
    }

    /**
     * Represents event task as a String object.
     *
     * @return String form of event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + formatDate() + ")";
    }
}