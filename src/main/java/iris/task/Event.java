package iris.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import iris.IrisException;

/**
 * Represents an Event
 */
public class Event extends Task {
    protected LocalDate at;

    /**
     * Creates a new Event object
     *
     * @param name name of the event
     * @param at   date for this event e.g. "2021-08-23"
     * @throws IrisException for invalid date
     */
    public Event(String name, String at) throws IrisException {
        super(name);
        try {
            this.at = LocalDate.parse(at);
        } catch (DateTimeParseException exception) {
            throw new IrisException("Invalid date provided.");
        }
    }

    /**
     * Converts Event object to String
     *
     * @return String representation of Event object
     */
    @Override
    public String toString() {
        return String.format(
                "[E]%s (at: %s)",
                super.toString(),
                this.at.format(DateTimeFormatter.ofPattern(DATE_FORMAT))
        );
    }

    /**
     * Converts Event object to Command
     *
     * @param index index of this Task in the TaskList
     * @return String representing the command to re-create this Event object
     */
    @Override
    public String toCommand(int index) {
        return String.format(
                "event %s /at %s\n%s",
                this.name,
                this.at,
                super.toCommand(index)
        );
    }
}
