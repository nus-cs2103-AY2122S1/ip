/**
 *  This class represents an Event
 *  An event: tasks that start at a specific time and ends at a specific time
 * @author Ryan Tian Jun
 */
public class Event extends Task {

    private String by;

    public Event(String description, Task.TYPE type, String by) {
        super(description, type);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + by + ")";
    }
}
