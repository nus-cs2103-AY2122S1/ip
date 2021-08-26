package operation;

/**
 * This is the Event class for event tasks.
 */
public class Event extends Task{
    protected String at;

    /**
     * Constructor for Event objects.
     * @param description input string
     * @param at event date
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Splits event string into body and event date.
     * @param input input string
     * @return new split event object
     */
    public static Event splitEvent(String input) {
        String[] partsOfEvent = input.split("/at ");
        String eventContent = partsOfEvent[0].substring(6);
        String at = partsOfEvent[1];
        return new Event(eventContent, at);
    }

    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + " (at: "
                + this.at
                + ")";    }
}
