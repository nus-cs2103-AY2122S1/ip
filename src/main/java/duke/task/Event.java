package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * An Event is a task that start at a specific time and ends at a specific time.
 */
public class Event extends Task {
    protected LocalDate at;

    /**
     * Creates an Event with the provided description and start and end date/time.
     * @param description The description of the Event task.
     * @param at The date/time of the Event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = LocalDate.parse(at);
    }

    /**
     * Converts the Event into a String that represents the Event.
     * @return The String representation of a Event.
     */
    @Override
    public String toString() {
        return String.format(
            "[%s]%s (at: %s)",
            TaskType.EVENT.toString(),
            super.toString(),
            this.at.format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
    }

    /**
     * Converts the Event into a String to be stored in Storage.
     * @return String to be stored
     */
    @Override
    public String toStorageString() {
        String taskString = super.toStorageString();
        return TaskType.EVENT.toString()
                + Task.STORAGE_STRING_DELIMITER
                + taskString
                + Task.STORAGE_STRING_DELIMITER
                + this.at;
    }
}
