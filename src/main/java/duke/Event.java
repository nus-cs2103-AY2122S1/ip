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

    /**
     * Creates an Event task from given task string.
     *
     * @return Event task.
     */
    public static Event create(String taskString, boolean isDone) {
        int timeStart = taskString.indexOf("at: ");
        String description = taskString.substring(7, timeStart - 2) + " ";
        String dateTime = taskString.substring(timeStart + 4, taskString.length() - 1);
        LocalDate date = LocalDate.parse(dateTime, DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
        return new Event(description, date, isDone);
    }
}