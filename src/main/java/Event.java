/**
 * Class for Event, a child class of Task.
 * @author Liew Jian Hong
 */
public class Event extends Task{
    /**
     * The date and time of the event.
     */
    protected String at;

    /**
     * Constructor for an Event task.
     * @param desc String array consisting of parsed description.
     */
    public Event(String[] desc) {
        super(desc[1], false);
        this.at = desc[2];
    }

    /**
     * Return a string representation of the event.
     * @return Return the type, completion status and description of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")\n";
    }
}
