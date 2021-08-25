/**
 * A Task that start at a specific date/time and
 * ends at a specific date/time.
 *
 * @author Lethicia
 */
public class Event extends Task{
    /** date/time of the event. */
    protected String at;

    /**
     * Constructor for an Event task.
     *
     * @param description the title or description for the task
     * @param at date/time of the task.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    //TODO
    public Event(String description, String at, boolean status) {
        super(description);
        this.at = at;
        this.isDone = status;
    }

    /**
     * Returns the file's details in the format "E,<isDone>,<desc>,<time>"
     * to be stored in the hard disk
     *
     * @return formatted string containing task details
     */
    public String getText() {
        return String.format("E,%s,%s,%s\n", this.isDone, this.description, this.at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
