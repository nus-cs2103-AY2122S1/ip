import java.time.LocalDateTime;

/**
 * This class represents an event task.
 */

public class Event extends Task{
    /** Start or end time of the event. */
    private LocalDateTime eventTime;

    /**
     * Constructs an event using the given description and event time.
     *
     * @param description the given description.
     * @param eventTime the given event time.
     */
    public Event(String description, LocalDateTime eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    /**
     * Constructs an event using the given description, given complete state and event time.
     *
     * @param description the given description.
     * @param isDone the given complete state.
     * @param eventTime the given event time.
     */
    public Event(String description, boolean isDone, LocalDateTime eventTime) {
        super(description, isDone);
        this.eventTime = eventTime;
    }

    /**
     * Returns a string representation of the event.
     *
     * @return a string representation of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventTime.format(Ui.dateTimeFormatter) + ")";
    }

    /**
     * Returns a string representation of the event following .txt format.
     *
     * @return a string representation of the event following .txt format.
     */
    @Override
    public String toTxtFormat() {
        return "E" + Parser.SPLITER + super.toTxtFormat()
                + Parser.SPLITER + eventTime.format(Parser.inputDateTimeFormatter);
    }
}
