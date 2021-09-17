package bruh.task;

/**
 * Represents an event occurring at a specified date and time.
 */
public class EventTimedTask extends TimedTask {
    private static final char SYMBOL = 'E';

    /**
     * Constructor for an event occurring at a specified date and time.
     *
     * @param description The description of the event.
     * @param time        The time of the event.
     * @param flag        The flag separating the description and deadline.
     */
    public EventTimedTask(String description, String time, String flag) {
        super(description, SYMBOL, time, flag);
    }
}
