package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class that encapsulates an Event task.
 */
public class Event extends Task {
    private LocalDate eventDate;

    /**
     * Returns a new Event object.
     * @param eventName The user input.
     */
    public Event(String eventName) {
        super(eventName.substring(6, eventName.indexOf("/at")));
        int start = eventName.indexOf("/at");
        this.eventDate = LocalDate.parse(eventName.substring(start + 4));
    }

    /**
     * Returns a new Event object.
     * @param eventName The Event as written in the save file.
     * @param isDone Whether the task is done.
     */
    public Event(String eventName, boolean isDone) {
        super(eventName.substring(0, eventName.indexOf("(at:")), isDone);
        int start = eventName.indexOf("(at:") + 5;
        this.eventDate = LocalDate.parse(eventName.substring(start, start + 11),
                DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public LocalDate getEventDate() {
        return this.eventDate;
    }

    /**
     * Overrides the toString method in Task.
     * @return The String representation of the Event object.
     */
    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + "(at: "
                + this.eventDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
    }
}
