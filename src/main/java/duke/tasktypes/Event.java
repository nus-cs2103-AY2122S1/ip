package duke.tasktypes;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event class.
 */
public class Event extends Task {

    private final String date;

    /**
     * Constructor for Events class.
     *
     * @param description Description of event.
     * @param eventDate Date of event.
     */
    public Event(String description, String eventDate) {
        super(description);
        this.date = eventDate;
    }


    /**
     * Getter method for returning date as a String.
     *
     * @return Returns date.
     */
    public String getDate() {
        return this.date;
    }

    /**
     * Modifies inputted date.
     *
     * @return Date in a different format.
     */
    public String understandDate() {
        try {
            LocalDate date = LocalDate.parse(this.date);
            return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeException exception) {
            System.out.println(exception.getMessage());
        }
        return "";
    }


    /**
     * Returns in a format to save to file.
     */
    @Override
    public String saveToHardDisk() {
        return "E/" + this.getBooleanStatus() + "/" + this.getDescription() + "/" + this.date;
    }

    /**
     * Returns string format.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + understandDate() + ")";
    }
}
