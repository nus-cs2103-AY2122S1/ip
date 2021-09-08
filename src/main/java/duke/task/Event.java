package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event with a description and a 'done' status and an Event date.
 */
public class Event extends Task {

    protected LocalDate at;

    /**
     * The Constructor for an Event object.
     * @param description The description of the Event.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Retrieves the date that the Event occurs.
     *
     * @return The date that the Event occurs.
     */
    @Override
    public LocalDate getDate() {
        return at;
    }

    /**
     * Converts the Event into the specified format for
     * the text file from Storage.
     *
     * @return The file format representation of the Event.
     */
    @Override
    public String toFileFormat() {

        char done = '0';

        if (super.isDone) {
            done = '1';
        }
        return "E | " + done + " | " + getDescription() + " | " + getDate();
    }

    /**
     * Returns a string representation of the Event. Appends a
     * "[E]" to indicate it is an Event and the date in an "MMM d yyyy"
     * format.
     *
     * @return A string representation of the Event.
     */
    @Override
    public String toString() {
        return " [E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }


}
