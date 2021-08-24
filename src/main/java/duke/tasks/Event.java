package duke.tasks;

import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime at;

    /**
     * Instantiates an Event
     *
     * @param description description of the event
     * @param at scheduled date of the event
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    public Event(String description, boolean isDone, LocalDateTime at) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * Returns the string representation of this Event object.
     *
     * @return String representation of the Event Object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at.format(outputFormatter) + ")";
    }

    @Override
    public String toSaveString() {
        return "E" + super.toSaveString() + "|" + this.at.format(outputFormatter);
    }
}
