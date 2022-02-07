package duke;

/**
 * Class includes methods required for creating a event
 * and obtaining information relating to it.
 */
public class Event extends Task {
    private String time;
    private String type;

    /**
     * Creates an event.
     *
     * @param information refers to details of event
     * @param time refers to time event has occurred
     * @param type refers to type of task
     */
    public Event(String information, String time, String type) {
        super(information);
        this.type = type;
        this.time = time;
    }

    /**
     * Returns the event in a string format.
     *
     * @return event formatted as a string
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + time + ")";
    }

    /**
     * Returns the details about the event.
     *
     * @return event details
     */
    public String getDetails() {
        return time;
    }

    /**
     * Returns the type of task.
     *
     * @return task type
     */
    public String getType() {
        return type;
    }
}
