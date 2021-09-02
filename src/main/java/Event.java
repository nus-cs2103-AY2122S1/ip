/**
 * A Task with a date and time.
 * @author Thomas Hogben
 */
public class Event extends DateAndTimeTask {

    /**
     * Deprecated.
     * @param description The description of the Event.
     */
    public Event(String description) {
        super(description);
    }

    /**
     * @param description The description of the Event.
     * @param details The date and time of the Event.
     */
    public Event(String description, String details) throws DukeException {
        super(description, details);
    }

    public Event(String description, String details, boolean isDone) throws DukeException {
        super(description, details, isDone);
    }

    @Override
    public String getSave() {
        return "E" + super.getSave();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
