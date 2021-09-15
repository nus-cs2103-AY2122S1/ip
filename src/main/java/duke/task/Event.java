package duke.task;

import duke.DukeException;
import duke.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
        String[] desc_date = new String[0];
        try {
            desc_date = Task.splitDescriptionAndDate(args, "by");
        } catch (DukeException e) {
            Ui.showLoadingError(e.getMessage());
        }
        String desc = desc_date[0];
        String dateString = desc_date[1];
        LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("MMM d yyyy"));
        return new Event(desc, date);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
