package banana;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;


/**
 * This class handles event
 * types of tasks.
 *
 * @author: Ravi Ananya
 **/

class Event extends Task {

    protected LocalDate date;
    protected String timing;

    /**
     * Constructor for Event.
     *
     * @param description user input.
     * @param timing      date, day and/or time.
     */
    public Event(String description, String timing) {
        super(description);
        this.timing = timing;
    }

    /**
     * Constructor for Event.
     *
     * @param description user input.
     * @param date        for official dates
     * @param timing      date, day and/or time.
     */
    public Event(String description, LocalDate date, String timing) {
        super(description);
        this.date = date;
        this.timing = timing;
    }

    /**
     * Gets the event.
     *
     * @return the event.
     */
    public String getEvent() {
        if (date != null) {
            return date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + timing;
        } else {
            return timing;
        }
    }

    @Override
    public String toString() {
        if (date == null) {
            return "[E]" + super.toString() + " (at: " + timing + ")";
        } else {
            return "[E]" + super.toString() + " (at: " +
                    date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + timing + ")";
        }
    }

}
