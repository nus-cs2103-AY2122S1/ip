package duke.task;

import java.time.LocalDate;

/**
 * Encapsulates an event task.
 */
public class Event extends Task {
    protected LocalDate at;

    /**
     * Constructor for this event.
     *
     * @param description Details of event.
     * @param at Date of event.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns string of event in the format to be saved.
     *
     * @return String representation of event to be saved.
     */
    @Override
    public String saveTaskFormat(){
        return "E" + super.saveTaskFormat() + String.format("|%s", at);
    }

    /**
     * Returns string of event in list.
     *
     * @return String representation of event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
