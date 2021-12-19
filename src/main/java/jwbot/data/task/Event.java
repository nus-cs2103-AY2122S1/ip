package jwbot.data.task;

/**
 * Class for the event task
 *
 * @author Yim Jaewon
 */
public class Event extends Task {

    /**
     * The string that tells the date or time of the event
     */
    protected String at;

    /**
     * The constructor of the event task
     *
     * @param description the description of the event
     * @param at          the date or time of the event
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(Event event) {
        super(event.description, event.isDone);
        this.at = event.at;
    }

    /**
     * override toString method for easier printing
     *
     * @return the stingified task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
