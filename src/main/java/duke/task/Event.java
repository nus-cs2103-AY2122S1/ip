package duke.task;

/**
 * Event is a Task that encapsulates the attributes and behaviour of a Task with a duration.
 *
 * @author leezhixuan
 */
public class Event extends Task {
    private String duration;

    /**
     * Creates an instance of Event
     *
     * @param name Name of Event
     * @param duration Time between start and end of the Event
     */
    public Event(String name, String duration) {
        super(name);
        this.duration = duration;
    }

    @Override
    public String logo() {
        return "[E]";
    }

    @Override
    public String toString() {
        return super.getName() + " (at: " + this.duration + ")";
    }

    public String getDuration() {
        return this.duration;
    }
}
