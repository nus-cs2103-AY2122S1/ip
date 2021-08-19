/**
 * A type of task that requires event details and extends from Task
 *
 * @author: Wei Yangken
 */
public class Event extends Task{

    private String event;

    /**
     *  Constructor to create an EVENT task
     * @param name Name of task
     * @param eventDetails Location or time of event
     */
    Event(String name, String eventDetails) {
        super(name);
        this.event = eventDetails;
    }

    /**
     * Returns the name of the task in a format that shows type of task and its completion status
     * @return Task as a formatted string
     */
    @Override
    public String toString() {
        if(this.isDone()) {
            return String.format("[E][X] %s (at: %s)", this.getName(), this.event);
        } else {
            return String.format("[E][ ] %s (at: %s)", this.getName(), this.event);
        }
    }
}
