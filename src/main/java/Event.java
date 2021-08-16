/**
 * A Event-type Task consisting of event details, day and time.
 */
public class Event extends Task {
    /**
     * The day and time in string
     */
    protected String at;

    /**
     * Constructs a instance of Event that consist of event details, day and time
     *
     * @param taskDetails Description of the task
     * @param at day and time in string
     */
    public Event(String taskDetails, String at) {
        super(taskDetails);
        this.at = at;
    }
    /**
     * Return the string representation of Event details with day and time, prefixed with [E]
     *
     * @return the string representation of Event details
     */
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

}