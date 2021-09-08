package retriever.task;

/**
 * This class keeps track of event type tasks.
 */
public class Event extends Task {
    private TaskDateAndTime at;

    /**
     * Constructor, to initialize an event task.
     *
     * @param description Description of the task.
     * @param at What time the event is at.
     */
    public Event(String description, TaskDateAndTime at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns a boolean suggesting if the event task
     * is due on the same date, as the date entered.
     *
     * @param date The date entered by the user, to be checked against.
     * @return A boolean, true, if the task is due on the same date as the date entered.
     */
    public boolean isOnDate(TaskDateAndTime date) {
        return date.equals(at);
    }

    /**
     * Returns the event task description and its status in an
     * organised format.
     *
     * @return A String including the event task details.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}

