package fan.cs2103t.duke.task;

/**
 * Represents a task of type "event".
 * <p>
 * This is a subclass of the <code>Task</code> class.
 * A task of type "event" has a specific event period.
 */
public class Event extends Task {

    private final String at;

    /**
     * Constructs a task with the specified description and type "event",
     * with an initial completion status "not done".
     *
     * @param description the description of the "deadline" task.
     * @param at the event period of the "event" task of type <code>String</code>
     */
    public Event(String description, String at) {
        super(description, TaskType.EVENT);
        this.at = at;
    }

    /**
     * Returns the description of this task with type "event" and its completion status.
     *
     * @return the detailed description of this "event" task.
     */
    @Override
    public String getDescriptionWithStatus() {
        return "[E]" + super.getDescriptionWithStatus() + " (at: " + at + ")";
    }

}
