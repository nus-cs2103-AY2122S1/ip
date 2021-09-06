package duke.tasks;
/**
 * 
 * This represents an Event task with a time duration.
 * 
 * @author Rishabh Anand
 * @version CS2103, AY21/22 Semester 1
 * 
 */

public class Event extends Task {
    protected String timing;

    public Event(String desc, String timing) {
        super(desc);
        this.timing = timing;
    }

    // returns string representation of the Event
    @Override
    public String toString() {
        return "[E]" 
                + super.toString()
                + " (at: "
                + this.timing
                + ")";
    }

    @Override
    public String toTextRepresentation() {
        String binaryStatus = this.isDone ? "1" : "0";
        return String.format("E; %s; %s; %s", binaryStatus, this.description.strip(), this.timing.strip());
    }
}
