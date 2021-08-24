/**
 * Represents the event that has time. .
 *
 * @author QIN GUORUI
 */
public class Event extends Task {
    /**
     * The time to do the event.
     */
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = dateAndTime(at);
    }

    @Override
    public String getActualTime() {
        return at;
    }

    @Override
    public boolean compareTime(String time) {
        return at.equals(time);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
