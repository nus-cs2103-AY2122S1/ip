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
        super(desc[1], Boolean.valueOf(desc[3]));
        this.at = desc[2];
    }

    @Override
    public String toWrite() {
        return "event--" + Boolean.toString(this.isDone) + "--" + this.desc + "--" + this.at + "\n";
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
