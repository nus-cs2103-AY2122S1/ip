package duke.task;

/**
 * Represents a special type of task, which contains the time for the event.
 */
public class Event extends Task {
    private String eventTime;

    /**
     * Constructor of Event.
     *
     * @param task The name of the event.
     * @param eventTime The time of the event.
     */
    public Event(String task, String eventTime) {
        super(task);
        this.eventTime = eventTime;
    }

    /**
     * Constructor of Event.
     *
     * @param task The name of the event.
     * @param isDone Whether the task is done or not.
     * @param eventTime The time of the event.
     */
    public Event(String task, boolean isDone, String eventTime) {
        super(task, isDone);
        this.eventTime = eventTime;
    }

    /**
     * Returns the String representation of the event task.
     *
     * @return The String representation of the event task.
     */
    @Override
    public String toString() {
        String finished = " ";
        if (this.isDone()) {
            finished = "X";
        }
        return "[E]" + "[" + finished + "] " + this.getTaskName() + " (at: " + this.eventTime + ")";
    }

    /**
     * Returns the representation of the event task when it is stored.
     *
     * @return the representation of the event task when it is stored.
     */
    @Override
    public String toStoredString() {
        int finished = this.isDone() ? 1 : 0;
        return "E | " + finished + " | " + this.getTaskName() + " | " + eventTime;
    }
}
