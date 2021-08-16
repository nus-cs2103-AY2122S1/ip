/**
 * Represents a specific task (an event) containing the description
 * and the starting and ending time of the task that the user wants
 * to add in his or her todo list.
 */
public class Event extends Task{
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
