/**
 * This class keeps track of event type tasks.
 */
public class Event extends Task{

    private String at;

    /**
     * Constructor, to initialize an event task.
     *
     * @param description Description of the task.
     * @param at What time the event is at.
     */
    public Event(String description, String at){
        super(description);
        this.at = at;
    }

    /**
     * Returns the event task description and its status in an
     * organised format.
     *
     * @return A String including the event task details.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}
