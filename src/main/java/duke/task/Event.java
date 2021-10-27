package duke.task;

/**
 * Event class which encapsulates event date.
 */
public class Event extends Task {
    private String atDate;

    /**
     * Constructor method of Event.
     *
     * @param description Description of an event.
     * @param at Time of an event.
     */
    public Event(String description, String at) {
        super(description);
        this.atDate = at;
    }

    /**
     * Returns the event in array format.
     *
     * @return Event in string array format.
     */
    @Override
    public String[] formatTaskInArray() {
        String doneIndicator;
        if (this.isDone()) {
            doneIndicator = "1";
        } else {
            doneIndicator = "0";
        }
        String[] str = new String[]{"E", doneIndicator, this.getDescription(), atDate};
        return str;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + atDate + ")";
    }
}