package Duke;

/**
 * A Task that can be added to the Task List
 * which has more details after the description.
 */
public class Event extends Task {

    protected String at;

    /**
     * A public constructor to create an Event Task.
     * @param description The description of the Event.
     * @param at The details of the Event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the string representation of the event.
     * @return the string representation of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "--(at: " + at + ")";
    }

    /**
     * Returns the string representation of the
     * event to be saved into the file.
     * @return the string representation of the
     *         event to be saved into the file.
     */
    @Override
    public String toStore() {
        return "[E]" + super.toString() + "--(at: " + at + ")";
    }
}