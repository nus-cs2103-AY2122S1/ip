package duke.task;

/**
 * Event class which encapsulates event date/time.
 */
public class Event extends Task {
    private DateTime at;

    /**
     * Constructor for Event class.
     *
     * @param description Description of event.
     * @param at Date/time of event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = new DateTime(at);
    }

    /**
     * Constructor for Event class.
     *
     * @param description Description of event.
     * @param at Data/time of event.
     * @param isDone Completion status of event.
     */
    public Event(String description, String at, Boolean isDone) {
        super(description);
        super.isDone = isDone;
        this.at = new DateTime(at);
    }

    /**
     * Formats task's data into a string for hard drive storage
     * and returns it.
     *
     * @return String containing task's data.
     */
    @Override
    public String getData() {
        return "Event // " + (super.getIsDone() ? 1 : 0) + " // " + super.getDescription()
                + " // " + at.getDate();
    }

    /**
     * Overrides Task class's toString method.
     *
     * @return A String Describing details of Event class.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.toString() + ")\n";
    }
}
