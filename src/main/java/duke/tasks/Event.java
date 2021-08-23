package duke.tasks;

import duke.tasks.Task;

public class Event extends Task {
    protected final String eventDetails;

    public Event(String description, String eventDetails) {
        super(description);
        this.eventDetails = eventDetails;
    }

    public Event(String description, String eventDetails, boolean isDone) {
        super(description);
        this.eventDetails = eventDetails;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + description + " (at: " + eventDetails + ")";
    }

    @Override
    public String getStatusString() { return "D@" + (isDone ? 1 : 0) + "@" + this.description + "@" + this.eventDetails; }
}
