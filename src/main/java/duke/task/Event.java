package duke.task;

/**
 * Event is a task that has a duration and timing.
 */
public class Event extends Task {
    protected String at;

    /**
     * Constructs an Event object.
     *
     * @param description Description of the event.
     * @param at Time of the event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at:" + at + ")";
    }

    /**
     * Converts the task into a string that can be store in the file.
     *
     * @return the text format of the task to be store in the file.
     */
    @Override
    public String toFileString() {
        return "E | " + super.toFileString() + " | " + at;
    }

}
