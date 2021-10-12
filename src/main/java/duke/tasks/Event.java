package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class that encaspulates am Event.
 * An Event is a Task with an associated Event datetime.
 */
public class Event extends Task {

    private LocalDateTime eventTime;

    /**
     * A constructor for an Event.
     *
     * @param description The String description of the Event
     * @param eventTime The LocalDateTime representing the event datetime
     */
    public Event(String description, LocalDateTime eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    /**
     * An alternate constructor for an Event that may be completed.
     *
     * @param description The String description of the Event
     * @param eventTime The LocalDateTime representing the Event datetime
     * @param isComplete A Boolean representing if the Event is completed
     */
    public Event(String description, LocalDateTime eventTime, Boolean isComplete) {
        super(description, isComplete);
        this.eventTime = eventTime;
    }

    /**
     * Returns an easily parsable, String file representation of an
     * Event for use in persistent storage.
     *
     * @return An easily parsable String representing the Event
     */
    @Override
    public String getFileRepr() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return "E" + super.getFileRepr() + " | " + this.eventTime.format(format);
    }

    /**
     * Returns a String representation of an Event for use in
     * the Duke UI.
     *
     * @return A user-friendly, readable String representing the Event
     */
    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[E]["
            + ((this.isCompleted()) ? "X] " : " ] ")
            + this.getDescription()
            + " (at: "
            + this.eventTime.format(format) + ")";
    }
}
