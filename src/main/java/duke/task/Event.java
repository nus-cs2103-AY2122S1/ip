package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event
 */
public class Event extends Task {

    protected LocalDate date;

    /**
     * Event constructor.
     * 
     * @param description Description of event.
     * @param date Date of event.
     */
    public Event(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    /**
     * Returns a Event from string input with description and date.
     *
     * @param args String input with description and date in
     *                  '[description] (by: [MMM d yyyy])' format.
     * @return Event from string input with description and date.
     */
    public static Event build(String args) {
        String[] desc_date = Task.splitDescriptionAndDate(args, "by");
        String desc = desc_date[0];
        String dateString = desc_date[1];
        LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("MMM d yyyy"));
        return new Event(desc, date);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Event)) {
            return false;
        }

        Event c = (Event) o;

        return c.description.equals(description) && c.date.equals(date);
    }
}
