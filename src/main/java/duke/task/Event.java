package duke.task;

/**
 * Represents the type of Task which is an Event.
 */
public class Event extends Task {
    /**
     * Represents the duration of the task
     */
    private String duration;

    /**
     * Constructs a Event object with given description and duration.
     *
     * @param description Description of Event.
     * @param duration Duration of Event.
     */
    public Event(String description, String duration) {
        super(description);
        this.duration = duration;
    }

    /**
     * Converts Event object into String form to save.
     *
     * @return String form of Event object to save.
     */
    @Override
    public String convertToString() {
        return "E|" + super.convertToString() + "|" + duration;
    }

    /**
     * Converts Event object into its String representation.
     *
     * @return String representation of Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + duration + ")";
    }
}
