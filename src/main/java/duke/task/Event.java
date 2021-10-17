package duke.task;

import java.time.LocalDate;

import duke.CommandList;

/**
 * Event task to show
 */
public class Event extends Task {

    private LocalDate at;

    /**
     * Event constructor
     * @param value String input from user.
     * @param at LocalDate of the event date.
     */
    public Event(String value, LocalDate at) {
        super(value);
        this.at = at;

    }

    @Override
    public LocalDate getTime() {
        return at;
    }

    @Override
    public CommandList getType() {
        return CommandList.EVENT;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
