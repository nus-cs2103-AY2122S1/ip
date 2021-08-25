package duke.task;

import duke.Parser;

import java.time.LocalDate;

/**
 * A Todo type task representation for Duke.
 */
public class Event extends Task {

    protected LocalDate at;

    /**
     * Create an Event task.
     *
     * @param description Description of the event.
     * @param at LocalTime that the event will occur at.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + new Parser().formatLocalDate(at) + ")";
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Event) {
            Event otherEvent = (Event) other;
            return this.description.equals(otherEvent.description)
                    && (this.isDone == otherEvent.isDone) && this.at.equals(otherEvent.at);
        }
        return false;
    }
}