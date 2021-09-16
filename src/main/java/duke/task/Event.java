package duke.task;

/**
 * Event class for implementing tasks that are events.
 *
 * @author Benjamin Lui
 */

public class Event extends Task {

    protected String start;

    /**
     * Constructor of the event class.
     * @param description the name of the event
     * @param start the time which the event starts
     */
    public Event(String description, String start) {
        super(description);
        this.start = start;
    }

    /**
     * Constructor of the event class.
     * @param description the name of the event
     * @param start the time which the event starts
     * @param isDone whether the event is done, based on its status icon
     */
    public Event(String description, String start, String isDone) {
        super(description, isDone);
        this.start = start;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + start + ")";
    }
}
