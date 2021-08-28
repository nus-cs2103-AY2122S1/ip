package duke.tasktypes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event class.
 */
public class Events extends Task {

    protected String date;
    protected LocalDate at;

    /**
     * Constructor for Events class.
     * @param description
     * @param eventDate
     */
    public Events(String description, String eventDate) {
        super(description, "E");
        this.date = eventDate;
    }

    /**
     * Modifies inputted date.
     * @return Date in a different format.
     */
    public String understandDate() {
        at = LocalDate.parse(this.date);
        return at.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Returns in a format to save to file.
     */
    @Override
    public String hardDiskSave() {
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
