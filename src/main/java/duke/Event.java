package duke;

public class Event extends Task {

    private final String at;

    /**
     * Creates an Event object to store Task details.
     *
     * @param description Description of Task.
     * @param at When the Event is held.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + at + ")";
    }
}
