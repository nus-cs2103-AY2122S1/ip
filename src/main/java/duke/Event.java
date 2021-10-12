package duke;

public class Event extends Task { //Starts and ends by a certain time

    protected String by; //Range of timing

    /**
     * Constructor that instantiates a Event class
     * @param description
     * @param by
     */
    public Event(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(" + by + ")";
    }
}
