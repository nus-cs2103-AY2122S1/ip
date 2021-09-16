package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;

/**
 * Class that encapsulates Event tasks.
 */
public class Event extends Task {

    protected String at;

    /**
     * Constructor for Event.
     *
     * @param description Description of Event task.
     * @param at Date of event.
     * @throws DukeException If user inpyt is invalid.
     */
    public Event(String description, String at) throws DukeException {
        super(description);
        if (at.equals("") || at.equals(" ")) {
            throw new DukeException("☹ OOPS!!! The time of the event must be indicated.");
        } else {
            try {
                LocalDate localDate = LocalDate.parse(at);
                this.at = localDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            } catch (DateTimeParseException e) {
                throw new DukeException("duke.task.Event date should be in a yyyy-mm-dd format.");
            }
        }
    }

    /**
     * Returns a String representation of the Event task.
     *
     * @return A String representation of the Event task
     */
    @Override
    public String toString() {
        return "\t[E]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * Returns a String representation of the date of the event.
     *
     * @return A String representation of the date of the event.
     */
    @Override
    public String additionalDates() {
        return this.at;
    }
}
