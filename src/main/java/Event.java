/**
 * A Task with a date and time.
 * @author Thomas Hogben
 */
public class Event extends DateAndTimeTask {

    /**
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

    public Event(String description, String eventDate, boolean isDone) {
        super(description, isDone);
        this.eventDate = eventDate;
    }

    @Override
    public String getSave() {
        return "E" +
                (this.isDone() ? "1" : "0") + "|" +
                this.getDescription() + "|" +
                this.eventDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
