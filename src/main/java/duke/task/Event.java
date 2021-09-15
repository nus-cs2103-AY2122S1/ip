package duke.task;

/**
 * Event class which encapsulates event date/time.
 */
public class Event extends Task {
    private DateTime atDateTime;

    /**
     * Constructor for Event class.
     *
     * @param description Description of event.
     * @param atDate Date of event.
     * @param atTime Time of event.
     */
    public Event(String description, String atDate, String atTime) {
        super(description);
        this.atDateTime = new DateTime(atDate, atTime);
    }

    /**
     * Constructor for Event class.
     *
     * @param description Description of event task.
     * @param atDate Date of event.
     * @param atTime Time of event.
     * @param isDone Completion status of event.
     */
    public Event(String description, String atDate, String atTime, Boolean isDone) {
        super(description);
        super.isDone = isDone;
        this.atDateTime = new DateTime(atDate, atTime);
    }

    /**
     * Formats task's data into a string for hard drive storage
     *
     * @return String containing task's data.
     */
    @Override
    public String getData() {
        return "Event // " + (super.getIsDone() ? 1 : 0) + " // " + super.getDescription()
                + " // " + atDateTime.getDate() + " // " + atDateTime.getTime();
    }

    /**
     * Overrides Task class's toString method.
     *
     * @return A String Describing details of Event class.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (by: " + atDateTime.getFormattedDate() + " "
                + atDateTime.getFormattedTime() + ")\n";
    }
}
