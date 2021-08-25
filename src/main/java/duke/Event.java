package duke;

public class Event extends Task {
    protected String by;

    /**
     * A constructor to create an Event object
     *
     * @param description The description of an Event object
     * @param by The deadline of an Event object
     */
    public Event(String description, String by) {
        super(description);
        this.by = by.equals("") ? "at: " : by;
        super.deadline = this.by;
    }

    /**
     * Returns the string representation of a Event object
     *
     * @return The string representation of a Event object
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(" + by + ")";
    }
}
