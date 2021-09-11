package mango.task;

/**
 * Represents a task that is an event. An <code>Event</code> object corresponds to a
 * <code>Task</code> that has a description, a completion status, and a date on which it happens.
 */
public class Event extends Task {
    protected String date;

    /**
     * Constructor for an Event.
     *
     * @param description The description of the event.
     * @param date The date on which the event occurs.
     * @param tag The tag attached to the event.
     */
    public Event(String description, String date, String tag) {
        super(description, tag, false);
        this.date = date;
    }

    /**
     * Constructor for an Event, initialised with a completion status.
     *
     * @param description The description of the event.
     * @param date The date on which the event occurs.
     * @param status The completion status of the event.
     * @param tag The tag attached to the event.
     */
    public Event(String description, String date, String status, String tag) {
        super(description, tag, status.equals("true"));
        this.date = date;
    }

    /**
     * Returns the string representation of the type of task.
     *
     * @return The string representation of the type of task.
     */
    @Override
    public String getType() {
        return "E";
    }

    /**
     * Returns the description of the event.
     *
     * @return The description of the event.
     */
    @Override
    public String getDescription() {
        return String.format("%s (at: %s)", this.description, this.date);
    }

    /**
     * Returns the string representation of the event for saving.
     *
     * @return The string representation of the event.
     */
    @Override
    public String getSaveFormatString() {
        return String.format("%s:%s:%s:%s:%s\n", this.getType(), this.getStatus(), this.description, this.date, this.tag);
    }
}
