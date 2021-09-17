package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event is a task that represents that should be done at a specific time.
 */
public class Event extends Task {
    /** Datetime of event taking place. */
    private LocalDateTime dateTime;

    /**
     * Constructs event with date time.
     *
     * @param done task is completed.
     * @param taskName name of task.
     * @param dateTime at what time the event is happening.
     */
    public Event(boolean done, String taskName, LocalDateTime dateTime) {
        super(done, taskName);
        this.dateTime = dateTime;
    }

    /**
     * Turns Event into a string for saving to memory.
     *
     * @return concise string representation of Event.
     */
    @Override
    public String encode() {
        return String.format("E|%s|%s", super.encode(), dateTime);
    }

    /**
     * Turns task into a human readable string form.
     *
     * @return string representation of Event.
     */
    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM d yyyy HHmm");
        return String.format("[E]%s (at: %s)", super.toString(), this.dateTime.format(dateTimeFormatter));
    }

    /**
     * Changes datetime value of Event.
     *
     * @param dateTime date time value to be changed to.
     */
    @Override
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
