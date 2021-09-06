package bruh.task;

/**
 * Represents an event occurring at a specified date and time.
 */
public class Event extends TimedTask {
    private static final char SYMBOL = 'E';
    private static final String STRING_FORMAT = "%s (at: %s)";

    /**
     * Constructor for an event occurring at a specified date and time.
     *
     * @param description The description of the event.
     * @param time The time of the event.
     */
    public Event(String description, String time) {
        super(description, SYMBOL, time);
    }

    @Override
    public String toString() {
        return String.format(STRING_FORMAT, super.toString(), getDateTimeDesc());
    }
}
