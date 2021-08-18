public class Event extends Task{

    private final String at;

    /**
     * Constructor for an event task.
     *
     * @param description String describing the event task.
     * @param at String describing the time of the event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Get string representation of event task.
     *
     * @return String describing the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
