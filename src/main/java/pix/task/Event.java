package pix.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event to be conducted at a certain date.
 */
public class Event extends Task {
    private LocalDate eventDate;

    /**
     * Constructor for the event task.
     *
     * @param name Name of the task.
     * @param eventDate Date of the event.
     */
    public Event(String name, LocalDate eventDate) {
        super(name, false);
        this.eventDate = eventDate;
    }

    /**
     * Constructor for the event task.
     *
     * @param name Name of the task.
     * @param done States whether the task is done.
     * @param eventDate Date of the event.
     */
    public Event(String name, boolean done, LocalDate eventDate) {
        super(name, done);
        this.eventDate = eventDate;
    }

    /**
     * Retrieves the date of the event.
     *
     * @return Returns the date of the event.
     */
    public String getDate() {
        return eventDate.toString();
    }

    /**
     * Formats the Pix.task into the format to save it in a text file.
     *
     * @return Returns the text representation of the task.
     */
    @Override
    public String getSaveName() {
        return isDone ? "E|1|" + name + "|" + eventDate.toString() : "E|0|" + name + "|" + eventDate.toString();
    }

    /**
     * String representation of an event task.
     *
     * @return Returns the string representation of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + eventDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
