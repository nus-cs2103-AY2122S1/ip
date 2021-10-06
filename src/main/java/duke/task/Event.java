package duke.task;

/**
 * This class represents a {@code Event}.
 *
 * @author Eizabeth Chow
 */
public class Event extends Task {
    // deadline of the Event task
    private String deadline;

    /**
     * Constructs a {@code Event} object with the given title and deadline.
     *
     * @param title    Title of {@code Event}.
     * @param deadline Deadline of {@code Event}.
     */
    public Event(String title, String deadline) {
        super(title);
        this.deadline = deadline.trim();
    }

    /**
     * Constructs a {@code Event} object with the givem title, deadline and status.
     *
     * @param title    Title of {@code Event}.
     * @param deadline Deadline of {@code Event}.
     * @param isDone   Status of {@code Event}.
     */
    public Event(String title, String deadline, boolean isDone) {
        super(title, isDone);
        this.deadline = deadline.trim();
    }

    public Event setDate(String deadline) {
        this.deadline = deadline;
        return this;
    }

    /**
     * {@inheritDoc} Adds "E |" infront to indicate that it is a {@code Event} task.
     */
    public String toFileString() {
        return String.format("E | %s | %s", super.toFileString(), deadline);
    }

    /**
     * Returns a String representation of an Event task. Starts "[E]" to indicate
     * that it is an Event task.
     *
     * @return String representation of an Event.
     */
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), deadline);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Event) {
            Event otherEvent = (Event) other;
            return this.deadline.equals(otherEvent.deadline) && super.equals(other);
        }
        return false;
    }
}
