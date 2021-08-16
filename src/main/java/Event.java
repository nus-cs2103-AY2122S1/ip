/**
 * Tasks that start at a specific time and ends at a specific time.
 */
public class Event extends Task {
    private String time;

    public Event(String name, String time) {
        super(name);
        this.time = time;
    }

    public String toString() {
        if (isDone) {
            return "[E][X] " + name + " (at: " + time + ")"; 
        } else {
            return "[E][ ] " + name + " (at: " + time + ")"; 
        }
    }
}