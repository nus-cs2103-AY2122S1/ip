/**
 * This class implements a DukeList to be used in storing string from Duke
 *
 * @author Amos Tan
 * @version CS2103T AY21/22 Semester 1
 */

public class Event extends Task {
    /** The day of the event */
    protected String day;
    /** The time which the event takes place */
    protected String time;

    /**
     * Constructor for Event class
     * @param name the name of the event
     * @param day the day of the event
     * @param time the time which the event takes place
     */
    public Event(String name, String day, String time) {
        super(name);
        this.day = day;
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + day + " " + time + ")";
    }
}
