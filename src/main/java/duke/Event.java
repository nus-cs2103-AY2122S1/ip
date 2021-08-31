package duke;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Event task representing a task with a time to do at.
 */
public class Event extends Task {

    protected Date date;

    /**
     * Creates new Event task with description and date
     *
     * @param description Description of task
     * @param at Time to do task
     * @throws DukeException Whether time is in correct format
     */
    public Event(String description, String at) throws DukeException {
        super(description);
        try {
            this.date = new SimpleDateFormat("dd/MM/yyyy").parse(at);
        } catch (ParseException e) {
            throw new DukeException("Date format incorrect");
        }
    }

    /**
     * To string method to return formatted string
     *
     * @return Formatted String
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (by: " + date.toString() + ")";
    }
}
