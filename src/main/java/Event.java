/**
 * Class for the event task
 *
 * @author  Yim Jaewon
 */
public class Event extends Task {

    /**
     * The string that tells the date or time of the event
     */
    protected String at;

    /**
     * The constructor of the event task
     *
     * @param description the description of the event
     * @param by the date or time of the event
     */
    public Event(String description, String by) {
        super(description);
        this.at = by;
    }

    /**
     * override toString method for easier printing
     *
     * @return the stingified task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}