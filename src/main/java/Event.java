/**
 * The Event Class represents a task that starts and ends at specific timings.
 *
 * It contains information relating to the task:
 * - description
 * - isDone
 * - eventTime
 *
 * @author Benedict Chua
 */
public class Event extends Task {
    private String eventTime;

    public Event(String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (by: %s)", super.toString(), this.eventTime);
    }
}