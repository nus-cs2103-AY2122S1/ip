/**
 *  This class represents an Event.
 *  An event: tasks that start at a specific time and ends at a specific time.
 *
 * @author Ryan Tian Jun.
 */
public class Event extends Task {

    private String at;

    public Event(String description, Task.TYPE type, String at) {
        super(description, type);
        this.at = at;
    }

    public Event(TYPE type, boolean isDone, String description, String at) {
        super(type, isDone, description);
        this.at = at;
    }

    @Override
    public String getTime() {
        return at;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + at + ")";
    }
}
