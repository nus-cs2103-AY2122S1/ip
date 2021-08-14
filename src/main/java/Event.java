/**
 * This class implements a Deadline object which inherits from Task and additionally stores times of Event.
 *
 * CS2103T ip
 * AY21/22 Semester 1
 *
 * @author Kishendran Vendar Kon (Group G05)
 */
public class Event extends Task{
    /** String representing time of Event. */
    protected String during;

    /** Default constructor. */
    public Event(String description, String during) {
        super(description);
        this.during = during;
    }

    /**
     * Return the string representation of Event
     *
     * @return The string representation of Event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at:" + during + ")";
    }
}
