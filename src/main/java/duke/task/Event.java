package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime at;
    private DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("dd MMM uuuu HHmm");
    private DateTimeFormatter saveFormatter = DateTimeFormatter.ofPattern("dd/MM/uuuu HHmm");

    /**
     * Constructor
     *
     * @param description Task description
     * @param at Starts at
     */
    public Event(String description, LocalDateTime at) {
        super(description, false);
        this.at = at;
    }

    /**
     * Constructor for when reading file from storage
     *
     * @param description Task description
     * @param isDone Check complete status
     * @param at Starts at
     */
    public Event(String description, String isDone, LocalDateTime at) {
        super(description, isDone.equals("1"));
        this.at = at;
    }

    /**
     * Event to be updated/snoozed
     *
     * @param snoozedTime Updated time
     * @return Updated event in string format
     */
    public String snooze(LocalDateTime snoozedTime) {
        this.at = snoozedTime;
        return toString();
    }

    public LocalDateTime getAt() {
        return this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(displayFormatter) + ")";
    }

    @Override
    public String toSaveString() {
        return "E|" + super.toSaveString() + "|" + at.format(saveFormatter);
    }
}
