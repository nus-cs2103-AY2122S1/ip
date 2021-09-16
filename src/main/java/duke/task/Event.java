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
    protected String date = "";

    /**
     * Constructor for Event.
     *
     * @param description Description of Event task.
     * @param at Date of event.
     * @throws DukeException If user inpyt is invalid.
     */
    public Event(String description, String at) throws DukeException {
        super(description);

        try {
            LocalDate localDate = LocalDate.parse(at);
            this.date = localDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException e) {
            throw new DukeException("Event date should be in a yyyy-mm-dd format.");
        }

        if (description.isEmpty() || description == "" || description == " ") {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        } else {
            this.description = description;
        }

        if (at.isEmpty() || at == "" || at == " ") {
            throw new DukeException("☹ OOPS!!! The time of the event must be indicated.");
        } else {
            this.at = this.date;
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
