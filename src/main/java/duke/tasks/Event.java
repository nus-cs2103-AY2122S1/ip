package duke.tasks;
/**
 * Represent a task with a date.
 */
public class Event extends TaskWithDate{
    public Event(String description, String at, boolean done) {
        super(description,at);
        this.isDone = done;
    }

    public Event(String description, String at) {
        super(description,at);
    }
    
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.date + ")";
    }
}