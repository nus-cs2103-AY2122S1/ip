package duke.task;

import java.time.LocalDateTime;

import duke.DateTime;

public class Event extends Task {
    protected LocalDateTime time;

    /**
     * Constructs a new Event object.
     *
     * @param description Event description.
     * @param time Event time.
     */
    public Event(String description, LocalDateTime time) {
        super(description);
        this.time = time;
    }

    /**
     * Constructs a new Event object.
     *
     * @param description Event description.
     * @param time Event time.
     * @param isCompleted Event completion status.
     */
    public Event(String description, LocalDateTime time, boolean isCompleted) {
        super(description, isCompleted);
        this.time = time;
    }

    public LocalDateTime getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        String timeStr = DateTime.stringify(this.time);

        return "[E]" + super.toString() + " (at: " + timeStr + ")";
    }
}
