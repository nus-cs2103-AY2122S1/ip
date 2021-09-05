package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Creates Event objects.
 *
 * @author Leong Hong Fai
 */
public class Event extends Task {
    private LocalDate date;
    private String unformattedDate;

    /**
     * Creates Event object.
     * @param name Name of Event object.
     * @param date Date Event is happening at.
     */
    public Event(String name, String date) {
        super(name);
        assert !(name == null);
        this.date = LocalDate.parse(date);
        this.unformattedDate = date;
    }

    /**
     * Represents Event in a String format
     *
     * @return A string consisting of the information of the Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    public String getUnformattedDate() {
        return this.unformattedDate;
    }
}
