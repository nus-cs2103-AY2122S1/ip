package duke.task;

/**
 * Represents an event class which stores the time of the event.
 */
public class EventTask extends Task {
    protected String time;

    /**
     * Constructs an EventTask object.
     *
     * @param description Description of the task.
     * @param time Time of event.
     */
    public EventTask(String description, String time) {
        super(description);
        this.type = "E";
        this.time = time;
    }

    /**
     * Constructs an EventTask object.
     * @param description Description of the task.
     * @param isDone Status of the task.
     * @param time Time of event.
     */
    public EventTask(String description, boolean isDone, String time) {
        super(description, isDone);
        this.type = "E";
        this.time = time;
    }

    /**
     * Returns the time of the event.
     *
     * @return Time of event.
     */
    public String getTime() {
        return time;
    }

    public void setTime(String newTime) {
        time = newTime;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + this.time + ")";
    }
}
