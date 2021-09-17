package Wonderland.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Wonderland.Wonderland.task.Event class extends Wonderland.Wonderland.task.Task class and encapsulate an event Wonderland.Wonderland.task
 * that start at a specific time and ends at a specific time.
 */
public class Event extends Task {
    private final String symbol = "[E]";
    protected LocalDate at;

    /**
     * Constructor for an event Wonderland.Wonderland.task.
     *
     * @param description String description of event.
     * @param at LocalDate of event time.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns string for saving event Wonderland.Wonderland.task to data file.
     *
     * @return string for saving event Wonderland.Wonderland.task to data file.
     */
    @Override
    public String toFileEntry() {
        return "E" + "/next"+ super.isDone + "/next" + super.description + "/next" + this.at.toString();
    }

    /**
     * Returns string representation of event object.
     *
     * @return string representation of event object.
     */
    @Override
    public String toString() {
        return this.symbol + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) +")";
    }
}
