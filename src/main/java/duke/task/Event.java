package duke.task;

import duke.Parser;
import duke.Ui;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * This class represents an event task.
 */
public class Event extends Task {
    /**
     * Start or end time of the event.
     */
    private final LocalDateTime eventTime;

    /**
     * Constructs an event using the given description and event time.
     *
     * @param description The given description.
     * @param eventTime   The given event time.
     */
    public Event(String description, LocalDateTime eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    /**
     * Constructs an event using the given description, given complete state and event time.
     *
     * @param description The given description.
     * @param isDone      The given complete state.
     * @param eventTime   The given event time.
     */
    public Event(String description, boolean isDone, LocalDateTime eventTime) {
        super(description, isDone);
        this.eventTime = eventTime;
    }

    /**
     * Returns a string representation of the event.
     *
     * @return A string representation of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventTime.format(Ui.dateTimeFormatter) + ")";
    }

    /**
     * Returns a string representation of the event following .txt format.
     *
     * @return A string representation of the event following .txt format.
     */
    @Override
    public String toTxtFormat() {
        return "E" + Parser.SPLITER + super.toTxtFormat()
                + Parser.SPLITER + eventTime.format(Parser.inputDateTimeFormatter);
    }

    /**
     * Returns true if the given object is equal to this, otherwise false.
     *
     * @param o The given object.
     * @return True if the given object is equal to this, otherwise false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return super.equals(o) && Objects.equals(eventTime, event.eventTime);
    }
}
