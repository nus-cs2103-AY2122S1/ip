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
     * @param desc_date String input with description and date in
     *                  '[description] (by: [MMM d yyyy]' format.
     * @return Event from string input with description and date.
     */
    public static Event build(String desc_date) {
        desc_date = desc_date.replaceAll("\\(at: (.*)\\)", "/at $1");
        String[] input = desc_date.split(" /at ",2);
        try {
            return new Event(input[0], LocalDate.parse(input[1]));
        } catch (DateTimeParseException e) {
            LocalDate d = LocalDate.parse(input[1], DateTimeFormatter.ofPattern("MMM d yyyy"));
            return new Event(input[0], d);
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
