package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Events are a type of task, where you can add a date
 * representing the date of when the task occurs.
 */
public class Event extends Task {

    /**
     * The associated date for the event
     **/
    protected LocalDate at;

    /**
     * Constructs an event.
     *
     * @param description The description associated with the event.
     * @param at          The date by which the event should be completed.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the type of task.
     *
     * @return "E" representing the task is an event.
     */
    @Override
    public String getTaskType() {
        return "E";
    }

    /**
     * Returns the timing of the task.
     *
     * @return The date associated with the event.
     */
    @Override
    public LocalDate getTiming() {
        return at;
    }

    /**
     * Pretty-prints the event in a readable way.
     *
     * @return The string representation of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + at.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

}
