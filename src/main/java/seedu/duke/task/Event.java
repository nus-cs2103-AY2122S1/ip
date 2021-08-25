package seedu.duke.task;

import java.time.LocalDate;

/**
 * Class that encapsulates an duke.task.Event duke.task.
 */
public class Event extends Task {
    /**
     * String that indicates the time of the event.
     */
    protected LocalDate timeOfEvent;

    /**
     * Public constructor to create an duke.task.Event duke.task
     *
     * @param description Description of the event.
     * @param timeOfEvent Time of the event.
     */
    public Event(String description, LocalDate timeOfEvent) {
        super(description);
        this.timeOfEvent = timeOfEvent;
    }

    /**
     * String representation of an event duke.task.
     *
     * @return String representation of an event duke.task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.timeOfEvent +")";
    }

}
