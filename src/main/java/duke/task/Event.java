package duke.task;

public class Event extends Task {
    // deadline of the Event task
    private final String deadline;

    /**
     * Constructor of the Event object.
     *
     * @param title    title of the Event
     * @param deadline deadline of the Event
     */
    public Event(String title, String deadline) {
        super(title);
        this.deadline = deadline.trim();
    }

    public Event(String title, String deadline, boolean isDone) {
        super(title, isDone);
        this.deadline = deadline.trim();
    }

    public String toFileString() {
        return String.format("E | %s | %s", super.toFileString(), deadline);
    }

    /**
     * Return a String representation of an Event task. Starts "[E]" to indicate
     * that it is an Event task.
     *
     * @return String representation of an Event.
     */
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), deadline);
    }
}
