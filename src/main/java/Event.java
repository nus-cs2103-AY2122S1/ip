/**
 * A type of Task that keeps track of the date and time of a task and the task's description.
 */
public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns Event's String form.
     *
     * @return String.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}