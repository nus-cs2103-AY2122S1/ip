import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that starts and ends at a specific time.
 * E.g. team project meeting at Oct-10-2019 1300hrs
 */
public class Event extends Task {
    protected LocalDateTime at;

    /**
     * Class constructor
     * @param description Description of the event.
     * @param at Time and Date of event.
     */
    public Event(String description, String at) {
        super(description);
        String dateTime = String.format(at.replace(" ", "T") + ":00");
        this.at = LocalDateTime.parse(dateTime);
    }

    /**
     * Returns the date and time of the event formatted as [MMM-dd-yyyy HH:mm]
     *
     * @return String formatted date and time
     */
    public String getDateTime() {
        DateTimeFormatter dateTimeFormatObj = DateTimeFormatter.ofPattern("MMM-dd-yyyy HHmm");
        String formattedDate = String.format(at.format(dateTimeFormatObj) + "hrs");
        return formattedDate;
    }

    /**
     * Returns the string representation of the Event.
     *
     * @return String representation of Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.getDateTime() + ")";
    }
}
