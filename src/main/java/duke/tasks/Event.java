package duke.tasks;

import java.time.LocalDateTime;

/**
 * Class to handle the Event task.
 */
public class Event extends Task {

    private Event(String description, LocalDateTime time) {
        super(description, "E", time);
    }

    /**
     * Factory method to create a new event.
     *
     * @param description Description of the event.
     * @param time Time of the event.
     * @return a new instance of Event.
     */
    public static Event create(String description, LocalDateTime time) {
        return new Event(description, time);
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (at: %s)",
                getTaskType(), getStatusIcon(), getDescription(), getTime()
        );
    }
}
