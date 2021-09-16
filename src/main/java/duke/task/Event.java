package duke.task;

/**
 * A task of type Event.
 */
public class Event extends Task {

    protected String at;

    /**
     * Instantiates a new event object.
     *
     * @param description description of the task.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Makes deep copy of itself
     * @return Event deep copy of itself
     */
    @Override
    public Task duplicate() {
        return new Event(this.description, this.at);
    }

    /**
     * Generates the string representation of event.
     *
     * @return String representation of event.
     */
    @Override
    public String toString() {
        return "E " + super.toString() + " | " + at;
    }
}
