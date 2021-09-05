package janet;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task, which is a task that will happen at a given time.
 */
public class Event extends Task {
    protected String at;
    protected LocalDate atDate;

    /**
     * Class constructor which takes in the description of the event task and a
     * string that represents the time at which the event will take place. If the
     * time string is given in the form YYYY-MM-DD, a LocalDate object will be
     * created to represent this information.
     *
     * @param description Description of the event task
     * @param at          Time at which the event will take place
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
        try {
            atDate = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            atDate = null;
        }
    }

    /**
     * Returns the LocalDate object which represents the date that the event will take place
     *
     * @return Date of the event
     */
    public LocalDate getAtDate() {
        assert(atDate != null);
        return atDate;
    }

    /**
     * Returns a formatted String representing the date of the event in the form
     * MMM d YYYY.
     *
     * @return Formatted date string
     */
    public String parseAtDate() {
        assert(atDate != null);
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MMM d yyyy");
        return getAtDate().format(pattern);
    }

    /**
     * Returns the string representation of the event, including whether it
     * has been completed and when it will take place.
     *
     * @return String representation of event task
     */
    @Override
    public String toString() {
        String date = (atDate == null) ? at : parseAtDate();
        String parentString = super.toString();
        return String.format("[E]%s (at: %s)", parentString, date);
    }
}
