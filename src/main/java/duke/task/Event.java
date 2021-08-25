package duke.task;

/**
 * Event is a Task that encapsulates the attributes and behaviour of a Task with a duration.
 *
 * @author leezhixuan
 */
public class Event extends Task {
    private String duration;
    private String name;

    /**
     * Creates an instance of Event
     *
     * @param name Name of Event
     * @param duration Time between start and end of the Event
     */
    public Event(String name, String duration) {
        this.name = name;
        this.duration = duration;
    }

    @Override
    public String logo() {
        return "[E]";
    }

    @Override
    public String toString() {
        return this.name + " (at: " + this.duration + ")";
    }

    public String getDuration() {
        return this.duration;
    }
}
