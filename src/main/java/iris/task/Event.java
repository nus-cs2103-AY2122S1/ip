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
            throw new IrisException(Task.INVALID_DATE_ERROR);
        }
    }

    /**
     * Creates a new Event object with given TaskPriority
     * @param name          name of the deadline
     * @param at            due date for this deadline e.g. "2021-08-23"
     * @param taskPriority  priority level of this deadline
     * @throws IrisException
     */
    public Event(String name, String at, TaskPriority taskPriority) throws IrisException {
        super(name, taskPriority);
        try {
            this.at = LocalDate.parse(at);
        } catch (DateTimeParseException exception) {
            throw new IrisException(Task.INVALID_DATE_ERROR);
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
                "event%s %s /at %s\n%s",
                this.getPriorityIcon(),
                this.name,
                this.at,
                super.toCommand(index)
        );
    }
}
